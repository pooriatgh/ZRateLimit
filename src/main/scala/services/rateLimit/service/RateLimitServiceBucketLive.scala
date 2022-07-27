package services.rateLimit.service

import services.rateLimit.config.RateLimitConfig
import services.rateLimit.repository.RateLimitRepository
import zio._

final case class RateLimitServiceBucketLive(
    config: RateLimitConfig,
    repo: RateLimitRepository
) extends RateLimitService {

  override def isRateLimited(key: String): Boolean = ???

  override def increment(key: String): Unit = ???

}

object RateLimitServiceBucketLive {

  val layer: ZLayer[
    RateLimitConfig with RateLimitRepository,
    Throwable,
    RateLimitService
  ] =
    ZLayer.fromFunction(RateLimitServiceBucketLive.apply _)
}
