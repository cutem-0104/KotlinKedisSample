import java.sql.*
import java.util.*

object MysqlTest {
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
            if (resultset.isBeforeFirst) {
                println(Integer.toString(resultset.row) + ": resultset is before first")
            }
            if (resultset.isAfterLast) {
                println(Integer.toString(resultset.row) +  ": resultset is after last")
            }
            while (resultset!!.next()) {
                if (resultset.isFirst()) {
                    println(Integer.toString(resultset.row) +  ": resultset is first")
                } else if (resultset.isLast) {
                    println(Integer.toString(resultset.row) +  ": resultset is last")
                }

                println(resultset.getString("id") + ": " + resultset.getString("name"))
            }
            if (resultset.isAfterLast) {
                println(Integer.toString(resultset.row) +  ": resultset is after last")
            }
            if (resultset.isBeforeFirst) {
                println(Integer.toString(resultset.row) + ": resultset is before first")
            }
            if (resultset.next()) {
                println(Integer.toString(resultset.row))
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

        var address = "127.0.0.1"
        if (PlatformUtils.isWindows) {
            address = "192.168.99.100"
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            address +
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