import com.sxtanna.database.Kedis
import com.sxtanna.database.config.KedisConfig
import kotlin.system.exitProcess

object RedisTest {

    /**
     * 下記コマンドでredisサーバーを起動させる
     * docker run --name some-redis -d -p 6379:6379 redis redis-server --appendonly yes --requirepass redis
     */
    @JvmStatic fun main(args: Array<String>) {
        val server = KedisConfig.ServerOptions("192.168.99.100", 6379)
        val option = KedisConfig.UserOptions("redis", 1)
        val config: KedisConfig = KedisConfig(server, option)
        val kedis = Kedis(config)
        kedis.enable()
        val resource = kedis.resource()

        kedis {
            set("Username", "Sxtanna")
            println(get("Username"))
            exitProcess(0)
        }

    }
}