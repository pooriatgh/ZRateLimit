package client.rateLimit.server

import client.rateLimit.server.routes.FooRoutes
import zhttp.*
import zhttp.http.HttpApp
import zhttp.service.Server
import zio.*
import zio.Console.printLine

final case class RateLimitServerLive(fooRoutes: FooRoutes)
    extends RateLimitServerService {
  override def start(port: Int, host: String): Task[Unit] =
    val sequential = Schedule.recurs(10) andThen Schedule.spaced(1.second)
    for{
      _ <-     printLine("charging buckets").repeat(sequential)
      - <-     Server.start(port = port,http = allRoutes)
    } yield ()

  val allRoutes: HttpApp[Any, Throwable] = {
    fooRoutes.routes
  }

}

object RateLimitServerLive {
  val layer: ZLayer[FooRoutes, Throwable, RateLimitServerService] =
    ZLayer.fromFunction(RateLimitServerLive.apply(_))

  val service: ZIO[FooRoutes, Throwable, Nothing] = layer.launch
  
}
