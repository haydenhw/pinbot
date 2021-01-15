package example

import example.resources.ResouceController

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
        case Nil             => println(docString)
        case "start" :: tail => println("Starting loop")
        // list
        case "list" :: tail =>  ResouceController.tabulate 
        // import
        case "import" :: filehandle :: tail =>
          ResouceController.importCSV(filehandle)
        case "import" :: tail =>
          println("ERROR: file path to a valid .csv file is required");
          sys.exit(1)
        // add
        case "add" :: "--name" :: name :: "--url" :: url :: tail =>
          ResouceController.add(name, url)
        case "add" :: tail =>
          println("ERROR: name and url are required");
        // update
        case "update" :: "--id" :: id :: "--name" :: name :: "--url" :: url :: tail =>
          ResouceController.update(id, Some(name), Some(url))
        case "update" :: "--id" :: id :: "--url" :: url :: "--name" :: name :: tail =>
          ResouceController.update(id, Some(name), Some(url))
        case "update" :: "--id" :: id :: "--name" :: name :: tail =>
          ResouceController.update(id, Some(name), None)
        case "update" :: "--id" :: id :: "--url" :: url :: tail =>
          ResouceController.update(id, None, Some(url))
        case "update" :: tail =>
          println(
            "ERROR: update must include 'id' and at least one field to update "
          );
        // invalid
        case option :: tail =>
          println("Unknown option " + option)
      }
    }
    nextOption(arglist)
  }
}
