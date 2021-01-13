package example

import java.sql.{Connection, DriverManager, ResultSet}

object Postgres { // extends App  if you want to run this
  // classOf[org.postgresql.Driver]
  val con_str =
    "jdbc:postgresql://localhost:5432/pingbot?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_str)
  try {
    val stm = conn.createStatement(
      ResultSet.TYPE_FORWARD_ONLY,
      ResultSet.CONCUR_READ_ONLY
    )

    val rs = stm.executeQuery("SELECT * from resources")

    while (rs.next) {
      println(rs.getString("name"))
    }

  } finally {
    conn.close()
  }
}
