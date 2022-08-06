package client.rateLimit.config

import client.rateLimit.models.RateLimitConfigModel
import zio._

final case class RateLimitConfigInMemoryLive()
    extends RateLimitConfig {

  override def get: Task[RateLimitConfigModel] = {
    val config = RateLimitConfigModel(
      maxRequests = 10,
      timeWindow = 1000,
      maxRequestsPerIp = 10,
      timeWindowPerIp = 1000
    )
    ZIO.succeed(config)
  }

}

object RateLimitConfigInMemoryLive {
  val layer: ZLayer[Any, Nothing, RateLimitConfig] =
    ZLayer.fromFunction(RateLimitConfigInMemoryLive.apply _)
}
