import services.rateLimit.config.RateLimitConfigInMemoryLive
import services.rateLimit.proxy.{RateLimitProxyLive, RateLimitProxyService}
import services.rateLimit.repository.{RateLimitModel, RateLimitRepositoryInMemoryLive}
import services.rateLimit.service.RateLimitServiceBucketLive
import zio.stm.TMap
import zio.{ZIO, ZIOAppDefault, ZLayer}

object ZioMain extends ZIOAppDefault {

  def run: ZIO[Any, Throwable, Unit] = {

    ZIO
      .serviceWithZIO[RateLimitProxyService](_.start(8080, "localhost"))
      .provide(
        ZLayer.fromZIO(TMap.empty[String,RateLimitModel].commit),
        RateLimitProxyLive.layer,
        RateLimitServiceBucketLive.layer,
        RateLimitRepositoryInMemoryLive.layer,
        RateLimitConfigInMemoryLive.layer
      )

  }

}
