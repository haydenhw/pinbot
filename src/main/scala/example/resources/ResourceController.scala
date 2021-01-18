package example.resources

import example.ResourceDAO
import example.resources.Resource
import example.services.Tabulator
import example.services.CSV

object ResourceController {
  def importCSV(filehandle: String) = {
    val resourcesList = CSV.toListOfLists(filehandle)
    val success = ResourceDAO.add(resourcesList)

    if (success)
      println("Resource imported successfully")
    else
      println("Failed to import resources")
  }

  def tabulate: Unit = {
    val resourcesList = ResourceDAO.findAll.map(_.toTableRow())
    val heading = List(List("ID", "STATE", "NAME", "CODE", "DATE-TIME", "URL"))
    val table = Tabulator.format(heading ++ resourcesList)
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

  def findAll: List[Resource] = {
    ResourceDAO.findAll
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
      println("Resource updated successfully")
    else
      println("Failed to update resource")
  }

  def updateStatus(
      id: Int,
      status: String,
      timeLastPinged: String
  ): Unit = {
    // TODO validate params
    val resource = ResourceDAO.findById(id);
    val success = ResourceDAO.update(
      resource.copy(status = Some(status), timeLastPinged = Some(timeLastPinged))
    )

    if (!success)
      println(s"Failed to update resource: $resource.name")
  }

  def deleteById(id: String) {
    val found = ResourceDAO.findById(id.toInt)
    val success = ResourceDAO.deleteById(found.id)

    if (success)
      println("Resource deleted successfully")
  }
}
