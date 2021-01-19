import example.services.Api
import example.resources._
import example.CLI

object Main extends App {
  println("=" * 100 + "\n") 

  CLI.parseArgs(args)

  println("\n" + "=" * 100)
}
