package example.services

import scala.collection.mutable.ArrayBuffer

object CSV {
  def toListOfArrays(filehandle: String): List[List[String]] = {
    // TODO support file handles with relative paths
    val bufferedSource = io.Source.fromFile(filehandle)
    val rows = ArrayBuffer[List[String]]()

    using(io.Source.fromFile(filehandle)) { source =>
      for (line <- source.getLines) {
        rows += line.split(",").map(_.trim).toList
      }
    }

    def using[A <: { def close(): Unit }, B](file: A)(parse: A => B): B =
      try {
        parse(file)
        // TODO add a catch block to print a nice error message if reading CSV fails
      } finally {
        file.close()
      }

    rows.toList
  }
}