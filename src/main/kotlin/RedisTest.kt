import com.sxtanna.database.Kedis
import com.sxtanna.database.config.KedisConfig
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub
import kotlin.system.exitProcess

object RedisTest {

    val local_address = "127.0.0.1"
    val server_address = "192.168.99.100"
    val server = KedisConfig.ServerOptions(local_address, 6379)
    val option = KedisConfig.UserOptions("redis", 0)
    val config: KedisConfig = KedisConfig(server, option)
    val kedis = Kedis(config)
    lateinit var resource: Jedis

    /**
     * 下記コマンドでredisサーバーを起動させる
     * docker run --name some-redis -d -p 6379:6379 redis redis-server --appendonly yes --requirepass redis
     */
    @JvmStatic fun main(args: Array<String>) {

        kedis.enable()
        resource = kedis.resource()

        kedis {
            println(get("key"))
            set("Username", "Sxtanna")
            resource.expire("Username", 3)
            set("num", 1000)
            Thread.sleep(5000)
            println(get("Username"))
            val num = get("num")
            set("num", num + 1000)
            println(get("num"))

            resource.rename("num", "new_num")
            println(get("new_num"))

            push("my_channel", "Hello! This is clientB")

            resource.save()
            exitProcess(0)
        }
    }

    fun setKey(key: String, value: String, second: Int = 0) {
        resource.set(key ,value)
        if (second > 0) {
            resource.expire(key, second)
        }
    }
}