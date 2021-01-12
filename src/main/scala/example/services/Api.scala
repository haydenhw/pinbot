package example.services

import sttp.client3._

object Api {
  def getStatusCode(url: String) {
    val sort: Option[String] = None
    val query = "http language:scala"

    // the `query` parameter is automatically url-encoded
    // `sort` is removed, as the value is not defined

    val request = basicRequest.get(
        uri"$url"
    )

    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)

    println(response.code)
  }
}
