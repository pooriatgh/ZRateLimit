package services.rateLimit.service

trait RateLimitService {
  def isRateLimited(key: String): Boolean
  def increment(key: String): Unit
}
