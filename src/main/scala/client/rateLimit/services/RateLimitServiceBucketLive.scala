package client.rateLimit.services

import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import client.rateLimit.models.RateLimitConfigModel.BucketConfig
import client.rateLimit.models.BucketModel
import client.rateLimit.repository.RateLimitRepository
import zio.*
import zio.Console.printLine

final case class RateLimitServiceBucketLive(
    repo: RateLimitRepository
) extends RateLimitService {

  override def checkIsLimited(key: String): Task[Boolean] = {
    (for {
      a <- repo.get(key)
    } yield a.isLimit).orElseFail(new Exception("Error"))
  }

  override def addToCheck(
      model: RateLimitModel
  ): Task[Unit] = {

    model match {
      case m: BucketModel =>
        val sequential = Schedule.recurs(2) andThen Schedule.spaced(2.second)
        for {
          _ <- printLine(s"charging buckets ${m.id}").repeat(sequential).fork
          _ <- repo.put(m.id, m).orElseFail(new Exception("Error"))
          _ <- printLine(s"Done")
        } yield ()

      case _ =>
        ZIO.fail(
          new RuntimeException(
            "Invalid config type passed here as a bucket config use BucketConfig when your limitService is a bucket."
          )
        )
    }

  }

  override def increment(key: String): Task[Unit] = {
    (for {
      oldValue <- repo.get(key).map(_.asInstanceOf[BucketModel])
      newValue = oldValue.copy(capacity = oldValue.capacity + 1)
      _ <- repo.put(key, newValue)
    } yield ()).orElseFail(new Exception("Error"))
  }
}
object RateLimitServiceBucketLive {

  val layer: ZLayer[RateLimitRepository, Throwable, RateLimitService] =
    ZLayer.fromFunction(RateLimitServiceBucketLive.apply _)
}
