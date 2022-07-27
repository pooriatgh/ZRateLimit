package services.rateLimit.proxy

import akka.stream.RateExceededException
import services.rateLimit.config.RateLimitConfig
import services.rateLimit.service.RateLimitService
import zio.*

final case class RateLimitProxyLive(rateLimitService: RateLimitService)
    extends RateLimitProxyService {
  override def start(port: Int, host: String): Task[Unit] = ???
}

object RateLimitProxyLive {
  val layer: ZLayer[RateLimitService, Throwable, RateLimitProxyService] =
    ZLayer.fromFunction(RateLimitProxyLive.apply(_))
}
