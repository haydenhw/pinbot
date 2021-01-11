package example

import example.resources.ResouceController  

object CLI {
  def parseArgs(args: Array[String]): Unit = {
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil => { println("Nil value found") }
        case "add" :: value :: tail => println(s"adding value $value")
        case "list" :: tail => {ResouceController.list}
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
