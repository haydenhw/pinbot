package example
import example.services.CSV
import example.resources.Resource


object ResourceDAO {
  def list: List[List[String]] = {
    val fh =
      "/home/hayden/revature/projects/zero/zero/src/main/scala/example/mock-resources.csv"
    CSV.toListOfLists(fh)
  }

  def add(name: String, url: String): Boolean = {
    println(s"DAO SAYS: RESOURCE ADDED -> $name $url")
    true
  }

  def findById(id: Int) : Resource = {
    Resource("fdsa", "http://coolurl bruh")
  }
}
