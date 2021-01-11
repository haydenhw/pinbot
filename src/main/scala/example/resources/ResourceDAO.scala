package example
import example.services.CSV

object ResourceDAO {
  def list: List[List[String]] = {
    val fh =
      "/home/hayden/revature/projects/zero/zero/src/main/scala/example/mock-resources.csv"
    CSV.toListOfArrays(fh)
  }

}
