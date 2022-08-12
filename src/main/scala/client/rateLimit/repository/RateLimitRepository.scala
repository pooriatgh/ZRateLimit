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
