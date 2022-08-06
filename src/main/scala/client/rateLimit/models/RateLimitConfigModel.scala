package client.rateLimit.models

import zio.Duration


sealed trait RateLimitConfigModel

object RateLimitConfigModel{
  case class SlidingWindowConfig(
                            maxRequests: Int,
                            timeWindow: Int,
                            maxRequestsPerIp: Int,
                            timeWindowPerIp: Int
                          ) extends RateLimitConfigModel

  case class BucketConfig(capacity: Int, refillInterval: Duration)
    extends RateLimitConfigModel
}
