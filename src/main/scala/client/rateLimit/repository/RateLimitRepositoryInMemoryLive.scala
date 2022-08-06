package client.rateLimit.repository
import client.rateLimit.models.RateLimitModel
import zio._
import zio.stm._




case class RateLimitRepositoryInMemoryLive(db:TMap[String, RateLimitModel]) extends RateLimitRepository {

  var dbUnsafe = Map.empty[String, RateLimitModel]

  override def get(key: String): Task[Option[RateLimitModel]] = {
    //ZIO.succeed(dbUnsafe.get(key))
    (for {
      elem <- db.get(key)
    } yield elem).commit
  }


  override def put(key: String, rateLimit: RateLimitModel): Task[Unit] = {
    //ZIO.succeed(dbUnsafe += (key -> rateLimit))
    (for{
      _ <- db.put(key, rateLimit)
      value <- db.values
    } yield println(value) ).commit
  }

  override def delete(key: String): Task[Unit] =
    ZIO.succeed(dbUnsafe -= key)
//    (for {
//      tMap <- db
//      _ <- tMap.delete(key)
//    } yield ()).commit
}

object RateLimitRepositoryInMemoryLive {

  val layer: ZLayer[TMap[String, RateLimitModel], Throwable, RateLimitRepository] =
    ZLayer.fromFunction(RateLimitRepositoryInMemoryLive.apply _)
}
