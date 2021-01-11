package example.resources
import example.ResourceDAO
import example.services.Tabulator

object ResouceController {
  def list: Unit = {
    val heading = List(List("NAME", "URL"))
    val resources = ResourceDAO.list
    val table = Tabulator.format(heading ++ resources)
    println(table)
  }
}
