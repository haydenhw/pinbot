package example.services

import sttp.client3._

/*
  Usage:
  val url = "https://lula.casa/ttflask/api/projects?userid=dVUeYfcDn"
  val res = Api.getStatusCode(url)
  println(res)
*/

object Api {
  def getStatusCode(url: String): String = {
    val request = basicRequest.get(uri"$url")
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)

    response.code.toString
  }
}
