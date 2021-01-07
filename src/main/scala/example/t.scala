/*
  - change api to work with todos
*/
object t {
  val usage = """
    Usage: mmlaln [--min-size num] [--max-size num] filename
    Usage: t add String
  """
  def main(args: Array[String]) {
    if (args.length == 0) println(usage)
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(map: OptionMap, list: List[String]): OptionMap = {
      def isSwitch(s: String) = (s(0) == '-')
      list match {
        case Nil => map
        case "add" :: value :: tail => {
            println(s"adding value $value")
            return map
        }
        case "--max-size" :: value :: tail => {
          nextOption(map ++ Map('maxsize -> value.toInt), tail)
        }
        case "--min-size" :: value :: tail =>
          nextOption(map ++ Map('minsize -> value.toInt), tail)
        case string :: opt2 :: tail if isSwitch(opt2) =>
          nextOption(map ++ Map('infile -> string), list.tail)
        case string :: Nil =>
          nextOption(map ++ Map('infile -> string), list.tail)
        case option :: tail =>
          println("Unknown option " + option)
          sys.exit(1)
      }
    }
    val options = nextOption(Map(), arglist)
    println(options)
  }
}
