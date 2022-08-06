package client.rateLimit.server.routes

import client.rateLimit.services.RateLimitService
import zhttp.http._
import zio._
import zio.json._

final case class FooRoutes(rateLimitService: RateLimitService) {
   val routes: Http[Any, Nothing, Request, Response] = Http.collectZIO[Request]{
     case Method.GET -> !! / "foo" =>
       ZIO.succeed(Response.json(("foo" -> "bar").toJson))
   }
}

object FooRoutes {
  val layer: ZLayer[RateLimitService, Nothing, FooRoutes] = ZLayer.fromFunction(FooRoutes.apply _)
}