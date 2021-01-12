package example

import example.resources.ResouceController  

object CLI {
  def parseArgs(args: Array[String]): Unit = {
    // TODO print a doc string
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil => { println("Nil value found") }
        case "add" :: "--name" :: name :: "--url" :: url :: tail => ResouceController.add(name, url)
        case "add" :: _ => println("ERROR: name and url are required"); sys.exit(1)
        case "import" :: filehandle :: tail => ResouceController.importCSV(filehandle)
        case "import" :: _ => println("ERROR: file path to a valid .csv file is required")
        case "list" :: tail => {ResouceController.list}
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
