package example

import example.services.Api
import example.resources.ResourceController
import example.resources.Resource

object Monitor {
  def init: Unit = {
    val t = new java.util.Timer()
    val fetchStatus = new java.util.TimerTask {
      def run() = {
        ResourceController.findAll.foreach(fetchStatusAndUpdateAsycn)
      }
    }
    t.schedule(fetchStatus, 0, 1000L)
    println("Resource monitoring initated...")
  }

  private def fetchStatusAndUpdateAsycn(resource: Resource): Unit = {
    val thread = new Thread {
      override def run {
        fetchStatusAndUpdate(resource)
      }
    }

    thread.start
  }

  private def fetchStatusAndUpdate(resource: Resource): Unit = {
    val datetimeNow = java.time.LocalDateTime.now().toString
    try {
      val status = Api.getStatusCode(resource.url)
      ResourceController.updateStatus(
        resource.id,
        status,
        datetimeNow
      )
    } catch {
      case e: Exception =>
        ResourceController.updateStatus(
          resource.id,
          "Connection Refused",
          datetimeNow
        )
    }
  }
}
