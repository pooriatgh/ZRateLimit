package services.rateLimit.config

case class RateLimitConfigModel(
    maxRequests: Int,
    timeWindow: Int,
    maxRequestsPerIp: Int,
    timeWindowPerIp: Int
)
