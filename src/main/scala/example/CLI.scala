package example

import example.resources.ResouceController  

object CLI {
  def parseArgs(args: Array[String]): Unit = {
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil => { println("Nil value found") }
        case "add" :: "--name" :: name :: "--url" :: url :: tail => ResouceController.add(name, url)
        case "add" :: _ => println("ERROR: You must include a name and url with the 'add' command"); sys.exit(1)
        case "list" :: tail => {ResouceController.list}
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
