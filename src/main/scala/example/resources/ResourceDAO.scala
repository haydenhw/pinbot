package example
import example.services.CSV
import example.resources.Resource


object ResourceDAO {
  def list: List[List[String]] = {
    val fh =
      "/home/hayden/revature/projects/zero/zero/src/main/scala/example/mock-resources.csv"
    CSV.toListOfLists(fh)
  }

  def add(resource: Resource): Boolean = {
    println(s"DAO SAYS: RESOURCE ADDED -> $resource")
    true
  }
}
