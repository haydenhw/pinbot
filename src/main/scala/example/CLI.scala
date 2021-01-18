package example

import example.resources.ResourceController
import example.Monitor
object CLI {
  def parseArgs(args: Array[String]): Unit = {
    // TODO add more docstring examples
    val docString = """
    Commands: import, add, update, list

    Examples:
      update --id 1 --name portfolio --url https://haydenhw.com
    """

    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil               => println(docString)
        case "monitor" :: tail => Monitor.init
        case "list" :: tail    => ResourceController.tabulate
        case "import" :: filehandle :: tail =>
          ResourceController.importCSV(filehandle)
        case "import" :: tail =>
          println("ERROR: file path to a valid .csv file is required");
          sys.exit(1)
        case "add" :: "--name" :: name :: "--url" :: url :: tail =>
          ResourceController.add(name, url)
        case "add" :: tail =>
          println("ERROR: name and url are required");
        case "delete" :: "--id" :: id :: tail =>
          ResourceController.deleteById(id)
        case "delete" :: tail => println("ERROR: please include the 'id' of the resource you want to delete")
        case "update" :: "--id" :: id :: "--name" :: name :: "--url" :: url :: tail =>
          ResourceController.update(id, Some(name), Some(url))
        case "update" :: "--id" :: id :: "--url" :: url :: "--name" :: name :: tail =>
          ResourceController.update(id, Some(name), Some(url))
        case "update" :: "--id" :: id :: "--name" :: name :: tail =>
          ResourceController.update(id, Some(name), None)
        case "update" :: "--id" :: id :: "--url" :: url :: tail =>
          ResourceController.update(id, None, Some(url))
        case "update" :: tail =>
          println(
            "ERROR: update must include 'id' and at least one field to update "
          );
        case option :: tail =>
          println("Unknown option " + option)
      }
    }
    nextOption(arglist)
  }
}
