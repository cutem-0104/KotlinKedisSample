import java.sql.*
import java.util.*

object SqlConnection {
    internal var conn: Connection? = null
    internal var username = "root"
    internal var password = "root"

    @JvmStatic fun main(args: Array<String>) {
        getConnection()
        executeMySQLQuery()
    }

    fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = conn!!.createStatement()
            stmt!!.executeQuery("USE app;")
            var sql = "SELECT * FROM users;"
            resultset = stmt!!.executeQuery(sql)

            if (stmt.execute(sql)) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {
                println(resultset.getString("id"))
                println(resultset.getString("name"))
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } finally {
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                conn = null
            }
        }
    }

    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "127.0.0.1" +
                            ":" + "33060" + "/" +
                            "",
                    connectionProps)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}