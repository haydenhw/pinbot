package example.resources

case class Resource(
    id: Int,
    name: String,
    url: String,
    status: Option[String] = None,
    timeLastPinged: Option[String] = None
) {
  private def getState(status: Option[String]): String = {
    // 200-299 status codes will be considered 'UP'
    // Anything else will be considered 'DOWN'
    status match {
      case Some(status) if status.charAt(0) == '2' => "UP"
      case Some(_)                                 => "DOWN"
      case None                                    => "-"
    }
  }

  def toTableRow(): List[String] = {
    List(
      id.toString,
      getState(status),
      name,
      status.getOrElse("-"),
      timeLastPinged.getOrElse("-"),
      url
    )
  }
}
