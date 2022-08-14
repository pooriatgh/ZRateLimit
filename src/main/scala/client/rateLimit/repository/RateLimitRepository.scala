package client.rateLimit.repository

import client.rateLimit.models.errors.RateLimitError
import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import zio.{Task, ZIO}

trait RateLimitRepository {
  def get(key: String): ZIO[Any, RateLimitError, RateLimitModel]
  def put(
      key: String,
      rateLimit: RateLimitModel
  ): ZIO[Any, RateLimitError, Unit]
  def delete(key: String): ZIO[Any, RateLimitError, Unit]
}

//accessors
object RateLimitRepository{
    def get(key: String): ZIO[RateLimitRepository, RateLimitError, RateLimitModel] =
        ZIO.serviceWithZIO[RateLimitRepository](_.get(key))
    def put(
        key: String,
        rateLimit: RateLimitModel
    ): ZIO[RateLimitRepository, RateLimitError, Unit] =
        ZIO.serviceWithZIO[RateLimitRepository](_.put(key, rateLimit))
    def delete(key: String): ZIO[RateLimitRepository, RateLimitError, Unit] =
        ZIO.serviceWithZIO[RateLimitRepository](_.delete(key))
}