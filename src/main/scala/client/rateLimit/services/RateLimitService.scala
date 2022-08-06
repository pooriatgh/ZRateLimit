package client.rateLimit.services

trait RateLimitService {
  def isRateLimited(key: String): Boolean
  def increment(key: String): Unit
}
