package example.resources

import example.ResourceDAO
import example.resources.Resource
import example.services.Tabulator
import example.services.CSV

object ResouceController {
  def importCSV(filehandle: String) = {
    val resourcesList = CSV.toListOfLists(filehandle)
    resourcesList.foreach(r => {
      // TODO validate name and url
      val name = r(0)
      val url = r(1)

      val success = ResourceDAO.add(name, url)

      if (success)
        println(s"Resource add successfully $name $url")
      else
        println(s"Failed to add resource $name $url")
    })

  }

  def list: Unit = {
    val heading = List(List("NAME", "URL"))
    val resources = ResourceDAO.list
    val table = Tabulator.format(heading ++ resources)
    println(table)
  }

  def add(name: String, url: String): Unit = {
    // TODO validate name and url
    val success = ResourceDAO.add(name, url)

    if (success)
      println(s"Resource add successfully $name $url")
    else
      println(s"Failed to add resource $name $url")
  }

  def update(name: Option[String], url: Option[String]): Unit = {
      var nextResource = ResourceDAO.findById(3);
      if (name.isDefined) nextResource = nextResource.copy(name = name.get)
      if (url.isDefined) nextResource =  nextResource.copy(url = url.get)

      println(nextResource)
  }
}
