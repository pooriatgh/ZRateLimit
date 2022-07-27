package services.rateLimit.repository

case class RateLimitModel(
    id: String,
    userId: String,
    rateLimit: Int,
    remaining: Int,
    resetAt: Long
)
