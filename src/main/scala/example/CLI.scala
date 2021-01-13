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
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil => { println("pc commands: import, add, update, list") } // TODO print a doc string
        case "start" :: tail => startLoop
        case "import" :: filehandle :: tail => ResouceController.importCSV(filehandle)
        case "import" :: tail => println("ERROR: file path to a valid .csv file is required"); sys.exit(1)
        case "add" :: "--name" :: name :: "--url" :: url :: tail => ResouceController.add(name, url)
        case "add" :: tail => println("ERROR: name and url are required"); sys.exit(1)
        case "update" :: "--name" :: name :: "--url" :: url :: tail => ResouceController.update(Some(name), Some(url))
        case "update" :: "--url" :: url :: "--name" :: name :: tail => ResouceController.update(Some(name), Some(url))
        case "update" :: "--name" :: name :: tail => ResouceController.update(Some(name), None)
        case "update" :: "--url" :: url :: tail => ResouceController.update(None, Some(url))
        case "update" :: tail => println("ERROR: file path to a valid .csv file is required"); sys.exit(1)
        case "list" :: tail => {ResouceController.list}
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
