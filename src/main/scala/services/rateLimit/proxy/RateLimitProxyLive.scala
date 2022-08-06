package services.rateLimit.proxy

import services.rateLimit.service.RateLimitService
import zio._


final case class RateLimitProxyLive(rateLimitService: RateLimitService)
    extends RateLimitProxyService {
  override def start(port: Int, host: String): Task[Unit] = ZIO.succeed(())
}

object RateLimitProxyLive {
  val layer: ZLayer[RateLimitService, Throwable, RateLimitProxyService] =
    ZLayer.fromFunction(RateLimitProxyLive.apply(_))
}
