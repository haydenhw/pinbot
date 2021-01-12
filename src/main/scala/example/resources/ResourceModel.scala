package example.resources

case class Resource( 
    name: String,
    url: String,
    status: Option[Int] = None,
    timeLastPinged: Option[String] = None,
) {}