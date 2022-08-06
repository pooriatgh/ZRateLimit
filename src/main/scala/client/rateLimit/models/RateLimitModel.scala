package client.rateLimit.models

import client.rateLimit.models.RateLimitConfigModel.SlidingWindowConfig

sealed trait RateLimitModel
object RateLimitModel {

  case class BucketModel(
      configModel: RateLimitConfigModel,
      id: String,
      capacity: Int
  ) extends RateLimitModel
  case class SlidingWindowModel(
      configModel: SlidingWindowConfig,
      id: String,
      rateLimit: Int,
      remaining: Int,
      resetAt: Long
  ) extends RateLimitModel

}
