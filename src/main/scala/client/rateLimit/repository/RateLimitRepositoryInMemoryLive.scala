package client.rateLimit.repository
import client.rateLimit.models.errors.RateLimitError
import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import zio.*
import zio.stm.*




case class RateLimitRepositoryInMemoryLive(db:TMap[String, RateLimitModel]) extends RateLimitRepository {

  var dbUnsafe = Map.empty[String, RateLimitModel]

  override def get(key: String) : ZIO[Any, RateLimitError, RateLimitModel] = {
    //ZIO.succeed(dbUnsafe.get(key))
    (for {
      elem <- db.get(key)
    } yield elem).commit.someOrFail[RateLimitModel,RateLimitError](RateLimitError.KeyNotFound(s"Key not found:$key"))
  }


  override def put(key: String, rateLimit: RateLimitModel): ZIO[Any, RateLimitError, Unit ] = {
    (for{
      _ <- db.put(key, rateLimit)
      _ <- db.values
    } yield() ).commit
  }

  override def delete(key: String): ZIO[Any, RateLimitError, Unit ] =
    (for{
      _ <- db.delete(key)
      _ <- db.values
    } yield() ).commit
}

object RateLimitRepositoryInMemoryLive {

  val layer: ZLayer[TMap[String, RateLimitModel], Throwable, RateLimitRepository] =
    ZLayer.fromFunction(RateLimitRepositoryInMemoryLive.apply _)
}
