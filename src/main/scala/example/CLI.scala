package example

import example.resources.ResouceController

object CLI {
  def startLoop: Unit = {
    val t = new java.util.Timer()
    var count = 0
    val task = new java.util.TimerTask {
      def run() = { count += 1; println(count) }
    }
    t.schedule(task, 1000L, 1000L)
  }

  def parseArgs(args: Array[String]): Unit = {
    val docString = """
    Commands: import, add, update, list

    Examples:
      update --id 1 --name portfolio --url https://haydenhw.com
    """

    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil             => println(docString) // TODO print a doc string
        case "start" :: tail => startLoop
        // list
        case "list" :: tail => { ResouceController.list }
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
          println("ERROR: name and url are required"); sys.exit(1)
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
          ); sys.exit(1)
        // invalid
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
