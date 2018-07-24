import com.squareup.moshi.Moshi
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

        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(User::class.java)

        val user = User(1, "cutem")
        val decodeJson = adapter.toJson(user)
        println(decodeJson)

        kedis {
            println("----start redis-------")
            set("user", decodeJson)
            val json = get("user")
            val fromJson = adapter.fromJson(json)
            println("JSON→クラス：$fromJson")

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

            setKey("a", "abc", 3)
            println(get("a"))
            Thread.sleep(5000)
            println(get("a"))

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

data class User(val id: Int, val name: String)