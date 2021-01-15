package example
import example.services.Api

object fetchAsycn {
  // def  creates a new thread that will call api with given param
  def makeThread(url: String) : Thread = {
    new Thread {
      override def run {
        Api.getStatusCode(url)
      }
    }
  }

}

object Monitor {
  def init: Unit = {
    val t = new java.util.Timer()
    var count = 0
    val task = new java.util.TimerTask {
      def run() = { count += 1; println(count) }
    }
    t.schedule(task, 0, 1000L)
    println("Resource monitoring initated...")
  }

  private def pingResrouces: Unit = {
    // get resources from controller
    // loop over resoures
    // call fetchStatusAndUpdate
  }

  private def fetchStatusAndUpdate(url: String): Unit = {
    // create a thread
    // inside the thread use url to call api then update the database with the result

  }
}
