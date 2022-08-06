import client.rateLimit.config.RateLimitConfigInMemoryLive
import client.rateLimit.models.RateLimitModel
import client.rateLimit.server.{RateLimitServerLive, RateLimitServerService}
import client.rateLimit.repository.RateLimitRepositoryInMemoryLive
import client.rateLimit.services.RateLimitServiceBucketLive
import zio.stm.TMap
import zio.{ZIO, ZIOAppDefault, ZLayer}

object ZioMain extends ZIOAppDefault {

  def run: ZIO[Any, Throwable, Unit] = {

    ZIO
      .serviceWithZIO[RateLimitServerService](_.start(8080, "localhost"))
      .provide(
        ZLayer.fromZIO(TMap.empty[String,RateLimitModel].commit),
        RateLimitServerLive.layer,
        RateLimitServiceBucketLive.layer,
        RateLimitRepositoryInMemoryLive.layer,
        RateLimitConfigInMemoryLive.layer,
        ZLayer.Debug.mermaid
      )

  }

}
