package services.rateLimit.proxy

import zio.Task
import zio.macros._


@accessible
trait RateLimitProxyService {
  def start(port:Int, host:String):Task[Unit]
}
