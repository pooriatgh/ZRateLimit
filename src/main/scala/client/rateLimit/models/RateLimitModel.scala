package client.rateLimit.models

import client.rateLimit.models.RateLimitConfigModel.{
  BucketConfig,
  SlidingWindowConfig
}

sealed trait RateLimitModel {
  def isLimit: Boolean
}
case class BucketModel(
    configModel: BucketConfig,
    id: String,
    capacity: Int
) extends RateLimitModel {
  override def isLimit: Boolean = capacity > configModel.capacity
}

case class SlidingWindowModel(
    configModel: SlidingWindowConfig,
    id: String,
    rateLimit: Int,
    remaining: Int,
    resetAt: Long
) extends RateLimitModel {
  override def isLimit: Boolean = remaining < configModel.maxRequests
}
