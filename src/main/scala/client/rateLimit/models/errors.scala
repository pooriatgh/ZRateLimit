package client.rateLimit.models

object errors {
  sealed trait RateLimitError
  object RateLimitError{
    case class KeyNotFound(key: String) extends RateLimitError
  }
}
