package client.rateLimit.server

import client.rateLimit.services.RateLimitService
import zhttp._
import zio._

final case class RateLimitServerLive(rateLimitService: RateLimitService)
    extends RateLimitServerService {
  override def start(port: Int, host: String): Task[Unit] = ???
}

object RateLimitServerLive {
  val layer: ZLayer[RateLimitService, Throwable, RateLimitServerService] =
    ZLayer.fromFunction(RateLimitServerLive.apply(_))
}
