import services.rateLimit.config.{RateLimitConfig, RateLimitConfigInMemoryLive}
import services.rateLimit.proxy.{RateLimitProxyLive, RateLimitProxyService}
import services.rateLimit.repository.{RateLimitRepository, RateLimitRepositoryInMemoryLive}
import services.rateLimit.service.{RateLimitService, RateLimitServiceBucketLive}
import zio.{ZIO, ZIOAppDefault}

object ZioMain extends ZIOAppDefault {


  def run =
    ZIO.serviceWithZIO[RateLimitProxyService](_.start(8080,"localhost")).provide(
      RateLimitProxyLive.layer,
      RateLimitServiceBucketLive.layer,
      RateLimitRepositoryInMemoryLive.layer,
      RateLimitConfigInMemoryLive.layer
    )

}
