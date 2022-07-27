package services.rateLimit.proxy

import zio.Task

trait RateLimitProxyService {
  def start(port:Int, host:String):Task[Unit]
}
