package client.rateLimit.services

import client.rateLimit.models.{RateLimitConfigModel, RateLimitModel}
import zio.Task

trait RateLimitService {
  def checkIsLimited(key: String): Task[Boolean]
  def addToCheck(model: RateLimitModel): Task[Unit]
  def increment(key:String):Task[Unit]
}
