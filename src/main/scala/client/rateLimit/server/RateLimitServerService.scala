package client.rateLimit.server

import zio.Task
import zio.macros._


@accessible
trait RateLimitServerService {
  def start(port:Int, host:String):Task[Unit]
}
