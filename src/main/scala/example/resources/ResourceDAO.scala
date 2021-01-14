package example

import java.sql.{Connection, DriverManager, ResultSet, Types}
import example.services.CSV
import example.resources.Resource
import scala.collection.mutable.ArrayBuffer

object ResourceDAO {
  // TODO set this with an environment variable
  val con_str =
    "jdbc:postgresql://localhost:5432/pingbot?user=postgres&password=postgres"

  def list: List[List[String]] = {
    // TODO make a connection utility
    val conn = DriverManager.getConnection(con_str)
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
          case _   => "DOWN"
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

  def update(resource: Resource): Boolean = {
    val conn = DriverManager.getConnection(con_str)

    try {
      val sql =
        "UPDATE resources SET name=?, url=?, status=?, time_last_pinged=? WHERE id=?"
      val stm = conn.prepareStatement(sql)

      stm.setString(1, resource.name)
      stm.setString(2, resource.url)

      if (resource.status.isDefined)
        stm.setString(3, resource.status.get)
      else
        stm.setNull(3, Types.VARCHAR)

      if (resource.status.isDefined)
        stm.setString(4, resource.timeLastPinged.get)
      else
        stm.setNull(4, Types.VARCHAR)

      stm.setInt(5, resource.id)

      if (stm.executeUpdate() != 0) true else false
    } finally {
      conn.close()
    }

  }

  def findById(id: Int): Resource = {
    // TODO make a connection utility
    val conn = DriverManager.getConnection(con_str)

    try {
      val sql = "SELECT * FROM resources WHERE id=?"
      val stm = conn.prepareStatement(sql)

      stm.setInt(1, id)
      val rs = stm.executeQuery()

      if (rs.next()) {
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val url = rs.getString("url")
        val maybeStatus = rs.getString("status")
        val status = if (rs.wasNull()) None else Some(maybeStatus)
        val maybeTimeLastPinged = rs.getString("time_last_pinged")
        val timeLastPinged =
          if (rs.wasNull()) None else Some(maybeTimeLastPinged)

        Resource(
          id = id,
          name = name,
          url = url,
          status = status,
          timeLastPinged = timeLastPinged
        )
      } else {
        // TODO change this to a better exception type
        throw new Exception(s"Resource with id=$id not found!")
      }
    } finally {
      conn.close()
    }
  }
}
