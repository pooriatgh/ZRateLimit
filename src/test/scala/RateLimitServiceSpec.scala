import RateLimitRepositorySpec.{suite, test}
import client.rateLimit.models.RateLimitConfigModel.BucketConfig
import client.rateLimit.models.{BucketModel, RateLimitModel}
import client.rateLimit.repository.{RateLimitRepository, RateLimitRepositoryInMemoryLive}
import client.rateLimit.services.{RateLimitService, RateLimitServiceBucketLive}
import zio.*
import zio.ZLayer.*
import zio.stm.TMap
import zio.test.{Spec, TestClock, ZIOSpecDefault, assertTrue}

object RateLimitServiceSpec extends ZIOSpecDefault {

  override def spec: Spec[Any, Any] = {
    suite("Bucket based Rate limit") {
      test("Insert API key and config") {

        for {

          model <- ZIO.succeed(BucketModel(configModel = BucketConfig(5,5.seconds),"costumer1",0))
          _ <- RateLimitService.addToCheck(model)
          _ <- RateLimitService.addToCheck(model.copy(id = "costumer2"))
          _ <- RateLimitService.increment("costumer1")
          _ <- RateLimitService.increment("costumer1")
          _ <- RateLimitService.increment("costumer1")
          _ <- RateLimitService.increment("costumer1")
          _ <- RateLimitService.increment("costumer1")
          _ <- RateLimitService.increment("costumer1")

          checkResult <- RateLimitService.checkIsLimited("costumer1")
        } yield assertTrue(checkResult)

      }
    }
  }.provide(
    fromZIO(TMap.empty[String, RateLimitModel].commit),
    RateLimitServiceBucketLive.layer,
    RateLimitRepositoryInMemoryLive.layer
  )
}
