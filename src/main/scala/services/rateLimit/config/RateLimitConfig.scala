package services.rateLimit.config

import zio.Task
import zio.macros._


@accessible
trait RateLimitConfig{
  def get:Task[RateLimitConfigModel]
}

