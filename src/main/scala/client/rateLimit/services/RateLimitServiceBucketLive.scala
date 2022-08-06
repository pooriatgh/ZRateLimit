package client.rateLimit.services

import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import client.rateLimit.models.RateLimitConfigModel.BucketConfig
import client.rateLimit.models.RateLimitModel.BucketModel
import client.rateLimit.repository.RateLimitRepository
import zio.*

final case class RateLimitServiceBucketLive(
    repo: RateLimitRepository
) extends RateLimitService {

  override def checkIsLimited(key: String): Task[Boolean] = {
    for {
      a <- repo.get(key)
      r <- ZIO
        .fromOption(a)
        .orElseFail(
          new RuntimeException(
            s"The $key not exist and you are " +
              s"looking for it to check the rate limit"
          )
        )
    } yield {
      r match {
        case bucket: BucketModel =>
          bucket.capacity > 0
        case _ =>
          throw new RuntimeException(
            "The model is not bucket model and you should try pass the correct model to isRateLimited method "
          )
      }
    }
  }

  override def addToCheck(
      model: RateLimitModel
  ): Task[Unit] = {

    model match {
      case config: BucketModel =>
        repo.put(config.id,config)

      case _ =>
        throw new RuntimeException(
          "Invalid config type passed here as a bucket config use BucketConfig when your limitService is a bucket."
        )
    }

  }
}
object RateLimitServiceBucketLive {

  val layer: ZLayer[RateLimitRepository, Throwable, RateLimitService] =
    ZLayer.fromFunction(RateLimitServiceBucketLive.apply _)
}
