package services.rateLimit.repository

trait RateLimitRepository {
    def get(key: String): Option[RateLimitModel]
    def put(key: String, rateLimit: RateLimitModel): Unit
    def delete(key: String): Unit
}
