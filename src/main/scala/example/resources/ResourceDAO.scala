package example

import java.sql.{Connection, DriverManager, ResultSet, Types}
import example.services.CSV
import example.resources.Resource
import scala.collection.mutable.ArrayBuffer

object ResourceDAO {
  // TODO set this with an environment variable
  classOf[org.postgresql.Driver].newInstance()
  val conStr =
    "jdbc:postgresql://localhost:5432/pingbot?user=postgres&password=postgres"

  def findAll: List[Resource] = {
    // TODO make a connection utility
    val conn = DriverManager.getConnection(conStr)
    val resources = ArrayBuffer[Resource]()
    try {
      val stm = conn.createStatement()
      val rs = stm.executeQuery("SELECT * from resources ORDER BY id ASC")

      while (rs.next) {
        // TODO define a method on Resources model to create an instance from a result set
        val id = (rs.getInt("id"))
        val name = rs.getString("name")
        val url = rs.getString("url")

        val maybeStatus = rs.getString("status")
        val status = if (rs.wasNull()) None else Some(maybeStatus)

        val maybeTimeLastPinged = rs.getString("time_last_pinged")
        val timeLastPinged =
          if (rs.wasNull()) None else Some(maybeTimeLastPinged)

        resources += Resource(
          id = id,
          name = name,
          url = url,
          status = status,
          timeLastPinged = timeLastPinged
        )
      }

    } finally {
      conn.close()
    }

    resources.toList
  }

  def findById(id: Int): Resource = {
    // TODO make a connection utility
    val conn = DriverManager.getConnection(conStr)

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

  def add(resources: List[List[String]]): Boolean = {
    val conn = DriverManager.getConnection(conStr)
    try {
      // TODO make a connection utility
      val sql = "INSERT INTO resources (name, url) VALUES (?, ?)"
      val stm = conn.prepareStatement(sql)
      conn.setAutoCommit(false)

      resources.foreach(r => {
        val name :: url :: Nil = r
        stm.setString(1, name)
        stm.setString(2, url)
        stm.executeUpdate()
        conn.commit()
      })

      stm.executeUpdate() != 0
    } finally {
      conn.close()
    }

  }

  def update(resource: Resource): Boolean = {
    val conn = DriverManager.getConnection(conStr)

    try {
      val sql =
        "UPDATE resources SET name=?, url=?, status=?, time_last_pinged=? WHERE id=?"
      val stm = conn.prepareStatement(sql)

      stm.setString(1, resource.name)
      stm.setString(2, resource.url)
      stm.setInt(5, resource.id)

      resource.status match {
        case Some(_) => stm.setString(3, resource.status.get)
        case None    => stm.setNull(3, Types.VARCHAR)
      }

      resource.timeLastPinged match {
        case Some(_) => stm.setString(4, resource.timeLastPinged.get)
        case None    => stm.setNull(4, Types.VARCHAR)
      }

      stm.executeUpdate() != 0
    } finally {
      conn.close()
    }
  }

  def deleteById(id: Int): Boolean = {
    val conn = DriverManager.getConnection(conStr)
    try {
    val sql = "DELETE from resources WHERE id=?"
    val stm = conn.prepareStatement(sql)

    stm.setInt(1, id)

    stm.executeUpdate() != 0
    } finally {
      conn.close()
    }
  }
}
