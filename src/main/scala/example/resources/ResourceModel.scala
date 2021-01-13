package example.resources

case class Resource( 
    id: Int,
    name: String,
    url: String,
    status: Option[String] = None,
    timeLastPinged: Option[String] = None,
) {}