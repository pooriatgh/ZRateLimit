package client.rateLimit.services

import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import zio._

trait RateLimitService {
  def checkIsLimited(key: String): Task[Boolean]
  def addToCheck(model: RateLimitModel): Task[Unit]
  def increment(key: String): Task[Unit]
}

object RateLimitService {

  def checkIsLimited(key: String): ZIO[RateLimitService, Throwable, Boolean] =
    ZIO.serviceWithZIO[RateLimitService](_.checkIsLimited(key))

  def addToCheck(model: RateLimitModel): ZIO[RateLimitService, Throwable, Unit] =
    ZIO.serviceWithZIO[RateLimitService](_.addToCheck(model))

  def increment(key: String): ZIO[RateLimitService, Throwable, Unit] =
    ZIO.serviceWithZIO[RateLimitService](_.increment(key))

}
