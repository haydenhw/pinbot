package example.resources

import example.ResourceDAO
import example.resources.Resource
import example.services.Tabulator
import example.services.CSV

object ResouceController {
  def importCSV(filehandle: String) = {
    val resourcesList = CSV.toListOfLists(filehandle)
    val success = ResourceDAO.add(resourcesList)

    if (success)
      println("Resource imported successfully")
    else
      println("Failed to import resources")
  }

  def list: Unit = {
    val heading = List(List("ID", "STATE", "NAME", "CODE", "DATE-TIME", "URL"))
    val resources = ResourceDAO.list
    val table = Tabulator.format(heading ++ resources)
    println(table)
  }

  def add(name: String, url: String): Unit = {
    // TODO validate name and url
    val success = ResourceDAO.add(List(List(name, url)))

    if (success)
      println(s"Resource add successfully $name $url")
    else
      println(s"Failed to add resource $name $url")
  }

  def update(
      id: String,
      name: Option[String],
      url: Option[String]
  ): Unit = {
    // TODO validate params
    var nextResource = ResourceDAO.findById(id.toInt);
    if (name.isDefined) nextResource = nextResource.copy(name = name.get)
    if (url.isDefined) nextResource = nextResource.copy(url = url.get)

    val success = ResourceDAO.update(nextResource)

    if (success)
      println("Resouce updated successfully")
    else
      println("Failed to update resource")
  }

  def update(
      id: String,
      name: Option[String],
      url: Option[String],
      status: Option[String],
      timeLastPinged: Option[String]
  ): Unit = {
    // TODO validate params
    var nextResource = ResourceDAO.findById(id.toInt);
    if (name.isDefined) nextResource = nextResource.copy(name = name.get)
    if (url.isDefined) nextResource = nextResource.copy(url = url.get)
    if (status.isDefined) nextResource = nextResource.copy(status = status)
    if (status.isDefined)
      nextResource = nextResource.copy(timeLastPinged = timeLastPinged)

    val success = ResourceDAO.update(nextResource)

    if (success)
      println("Resouce updated successfully")
    else
      println("Failed to update resource")
  }
}
