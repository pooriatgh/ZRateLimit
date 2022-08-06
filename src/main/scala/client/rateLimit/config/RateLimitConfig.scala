package client.rateLimit.config

import client.rateLimit.models.RateLimitConfigModel
import zio.Task
import zio.macros._


@accessible
trait RateLimitConfig{
  def get:Task[RateLimitConfigModel]
}

