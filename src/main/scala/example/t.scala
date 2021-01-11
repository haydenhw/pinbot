/*
  add:
    - list todos
      print todos want "1 - wash dishes"
    - run command ali add "new todo"
    - add todo to database using dao
       what info does

 */
/*
object ids {
  var idCount = 0

  def generate(): Int = {
    idCount += 1
    return idCount
  };
}

case class Todo(name: String, id: Int) {
  def printTodo: Unit = {
    println(s"$id - $name")
  }
}

object api { // extends App
  val exampleTodos = Seq("foo", "bar", "bing", "bang")
  var todos = Seq[String]() ++ exampleTodos;

  def addTodo(todo: String): Unit = {
    todos = todos :+ todo
    println(todo)
    println(todos)
  }

  def listTodos: Unit = {
    var count = 0
    val todoObjs = todos.map(t => new Todo(t, ids.generate))

    todoObjs.foreach(_.printTodo)
  }
}

object t {
  val usage = """
    Usage: mmlaln [--min-size num] [--max-size num] filename
    Usage: t add String
  """
  if (args.length == 0) println(usage)
  val arglist = args.toList
  type OptionMap = Map[Symbol, Any]

  def nextOption(list: List[String]): Unit = {
    list match {
      case Nil => { println("Nil value found") }
      case "add" :: value :: tail => {
        println(s"adding value $value")
        api.addTodo(value)
      }
      case "list" :: tail => {
        api.listTodos
      }
      case option :: tail =>
        println("Unknown option " + option)
        sys.exit(1)
    }
  }
  nextOption(arglist)
}
*/