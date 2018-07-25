import com.squareup.moshi.Moshi
import kotlin.system.exitProcess

object RedisTest {
    private val kedis = KedisUtils().kedis
    private val resource = KedisUtils().resource

    /**
     * 下記コマンドでredisサーバーを起動させる
     * docker run --name some-redis -d -p 6379:6379 redis redis-server --appendonly yes --requirepass redis
     */
    @JvmStatic fun main(args: Array<String>) {

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
        if (second > 0) resource.setex(key, second, value)
        else resource.set(key, value)
    }
}
