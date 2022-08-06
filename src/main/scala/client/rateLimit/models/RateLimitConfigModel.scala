package client.rateLimit.models

case class RateLimitConfigModel(
    maxRequests: Int,
    timeWindow: Int,
    maxRequestsPerIp: Int,
    timeWindowPerIp: Int
)
