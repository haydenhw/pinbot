/*
  - change api to work with todos
*/

object api {
  var todos = seq[String]();
  def addTodo(todo: String): Unit = {

  }
}

object t {
  val usage = """
    Usage: mmlaln [--min-size num] [--max-size num] filename
    Usage: t add String
  """
  def main(args: Array[String]) {
    if (args.length == 0) println(usage)
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(list: List[String]): Unit = {
      list match {
        case Nil => { println("Nil value found")}
        case "add" :: value :: tail => {
            println(s"adding value $value")
            // api.addTodo(value)
        }
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    nextOption(arglist)
  }
}
