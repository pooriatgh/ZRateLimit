package services.rateLimit.repository
import zio._
case class RateLimitRepositoryInMemoryLive() extends RateLimitRepository {

  override def get(key: String): Option[RateLimitModel] = ???

  override def put(key: String, rateLimit: RateLimitModel): Unit = ???

  override def delete(key: String): Unit = ???
}

object RateLimitRepositoryInMemoryLive {
  val layer: ZLayer[Any, Throwable, RateLimitRepository] =
    ZLayer.fromFunction(RateLimitRepositoryInMemoryLive.apply _)
}
