package client.rateLimit.repository

import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import zio.Task

trait RateLimitRepository {
    def get(key: String): Task[Option[RateLimitModel]]
    def put(key: String, rateLimit: RateLimitModel): Task[Unit]
    def delete(key: String): Task[Unit]
}
