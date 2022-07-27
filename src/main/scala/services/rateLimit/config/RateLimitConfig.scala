package services.rateLimit.config

import zio.Task

trait RateLimitConfig{
  def get:Task[RateLimitConfigModel]
}

