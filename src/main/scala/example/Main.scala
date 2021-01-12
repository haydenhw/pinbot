import example.services.Api
import example.resources._
import example.CLI

object Main extends App {
  println("=" * 40)
  // code here

  val url = "https://lula.casa/ttflask/api/projects?userid=dVUeYfcDn"
  Api.getStatusCode(url)

  // end code here
  println("=" * 40)
}
