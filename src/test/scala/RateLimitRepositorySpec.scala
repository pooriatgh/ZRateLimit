import client.rateLimit.models.RateLimitModel
import client.rateLimit.repository.{RateLimitRepository, RateLimitRepositoryInMemoryLive}
import zio._
import zio.stm.TMap
import zio.test._

object RateLimitRepositorySpec extends ZIOSpecDefault {

  override def spec: Spec[Any, Any] = {
    suite("logic") {
      test("Insert and retrieve a rate limit") {

        for{
          a <- RateLimitRepository.put("test",RateLimitModel("test", 1, 1,1))
          b <- RateLimitRepository.get("test")
        } yield assertTrue(b.isDefined)

      }
    }
  }.provide(
    ZLayer.fromZIO(TMap.empty[String,RateLimitModel].commit),
    RateLimitRepositoryInMemoryLive.layer)
}
