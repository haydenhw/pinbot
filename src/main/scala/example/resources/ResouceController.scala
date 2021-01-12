package example.resources

import example.ResourceDAO
import example.resources.Resource
import example.services.Tabulator

object ResouceController {
  def list: Unit = {
    val heading = List(List("NAME", "URL"))
    val resources = ResourceDAO.list
    val table = Tabulator.format(heading ++ resources)
    println(table)
  }

  def add(name: String, url: String): Unit = {
    // TODO validate name and url
    val resource = new Resource(name, url)

    val success = ResourceDAO.add(resource)

    if (success)
      println(s"Resources add successfully $name $url")
    else
      println("Failed to add resource")
  }
}
