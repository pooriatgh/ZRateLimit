import client.rateLimit.models.RateLimitConfigModel.BucketConfig
import client.rateLimit.models.{BucketModel, RateLimitConfigModel, RateLimitModel}
import client.rateLimit.repository.{RateLimitRepository, RateLimitRepositoryInMemoryLive}
import zio.*
import zio.stm.TMap
import zio.test.*
object RateLimitRepositorySpec extends ZIOSpecDefault {

  override def spec: Spec[Any, Any] = {
    suite("logic") {
      test("Insert and retrieve a rate limit") {

        for{
          service <- ZIO.service[RateLimitRepository]
          _ <- service.put("test",BucketModel(BucketConfig(5,5.seconds),"test",5))
          b <- service.get("test")
        } yield assertTrue(b!=null)

      }
    }
  }.provide(
    ZLayer.fromZIO(TMap.empty[String, RateLimitModel].commit),
    RateLimitRepositoryInMemoryLive.layer)
}
