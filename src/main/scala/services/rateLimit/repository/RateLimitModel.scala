package services.rateLimit.repository

case class RateLimitModel(
    id: String,
    rateLimit: Int,
    remaining: Int,
    resetAt: Long
)
