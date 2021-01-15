import example.services.Api
import example.resources._
import example.CLI

object Main extends App {
  print("\u001b[2J") // clear screen
  println("=" * 100)
  // code here
  CLI.parseArgs(args)
  // end code here
  println("=" * 100 + "\n" * 3)
}
