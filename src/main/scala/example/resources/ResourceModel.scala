package example.resources

case class ResourceModel( 
    name: String,
    url: String,
    status: Option[Int] = None,
    timeLastPinged: Option[String] = None,
) {}