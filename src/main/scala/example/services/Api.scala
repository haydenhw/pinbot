package example.services

import sttp.client3._

object Api {
  def getStatusCode(url: String): String = {
    val request = basicRequest.get(uri"$url")
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)

    response.code.toString
  }
}
