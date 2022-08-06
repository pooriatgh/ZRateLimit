package client.rateLimit.server

import zio.Task


trait RateLimitServerService {
  def start(port:Int, host:String):Task[Unit]
}
