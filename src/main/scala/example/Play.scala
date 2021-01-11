import example.resources.ResourceModel
import example.services.CSV



object Play extends App {
  println("=" * 40)
  // code here
  val fh = "/home/hayden/revature/projects/zero/zero/src/main/scala/example/mock-resources.csv"

  val list = CSV.toListOfArrays(fh)
  println(list(0)(1))

  // end code here
  println("=" * 40)
}
