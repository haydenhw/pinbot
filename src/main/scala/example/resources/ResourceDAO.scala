package example

import java.sql.{Connection, DriverManager, ResultSet}
import example.services.CSV
import example.resources.Resource
import scala.collection.mutable.ArrayBuffer

object ResourceDAO {
  // TODO set this with an environment variable
  val con_str =
    "jdbc:postgresql://localhost:5432/pingbot?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_str)

  def list: List[List[String]] = {
    val resources = ArrayBuffer[List[String]]()
    try {
      val stm = conn.createStatement()
      val rs = stm.executeQuery("SELECT * from resources")

      while (rs.next) {
        val id = (rs.getInt("id")).toString()
        val name = rs.getString("name")
        val url = rs.getString("url")

        var status = rs.getString("status")
        status = if (rs.wasNull()) "-" else status

        var timeLastPinged = rs.getString("time_last_pinged")
        timeLastPinged = if (rs.wasNull()) "-" else timeLastPinged

        // 200-299 status codes will be considered 'UP'
        // Anything else will be considered 'DOWN'
        val state = status.charAt(0) match {
          case '2' => "UP"
          case '-' => "-"
          case _ => "DOWN"
        }

        resources += List(id, state, name, status, timeLastPinged, url)
      }

    } finally {
      conn.close()
    }

    resources.toList
  }

  def add(name: String, url: String): Boolean = {
    println(s"DAO SAYS: RESOURCE ADDED -> $name $url")
    true
  }

  def findById(id: Int): Resource = {
    Resource(1, "fdsa", "http://coolurl bruh")
  }
}
