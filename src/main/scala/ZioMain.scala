
import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import client.rateLimit.server.{RateLimitServerLive, RateLimitServerService}
import client.rateLimit.repository.RateLimitRepositoryInMemoryLive
import client.rateLimit.server.routes.FooRoutes
import client.rateLimit.services.RateLimitServiceBucketLive
import zio.stm.TMap
import zio.*
import zio.Console.printLine

object ZioMain extends ZIOAppDefault {

  def run = {

     ZIO
      .serviceWithZIO[RateLimitServerService](_.start(8030, "localhost"))
      .provide(
        ZLayer.fromZIO(TMap.empty[String, RateLimitModel].commit),
        RateLimitServerLive.layer,
        RateLimitServiceBucketLive.layer,
        RateLimitRepositoryInMemoryLive.layer,
        FooRoutes.layer,
        ZLayer.Debug.mermaid
      )

  }

}
