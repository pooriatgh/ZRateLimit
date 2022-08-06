package client.rateLimit.models

case class RateLimitModel(
    id: String,
    rateLimit: Int,
    remaining: Int,
    resetAt: Long
)
