package client.rateLimit.server

import client.rateLimit.server.routes.FooRoutes
import zhttp._
import zhttp.http.HttpApp
import zhttp.service.Server
import zio._

final case class RateLimitServerLive(fooRoutes: FooRoutes)
    extends RateLimitServerService {
  override def start(port: Int, host: String): Task[Unit] =
    Server.start(port = 8066,http = allRoutes)

  val allRoutes: HttpApp[Any, Throwable] = {
    fooRoutes.routes
  }

}


object RateLimitServerLive {
  val layer: ZLayer[FooRoutes, Throwable, RateLimitServerService] =
    ZLayer.fromFunction(RateLimitServerLive.apply(_))
}
