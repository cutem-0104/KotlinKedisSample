import com.sxtanna.database.Kedis
import com.sxtanna.database.config.KedisConfig
import redis.clients.jedis.Jedis

class KedisUtils {
    var resource: Jedis
    var kedis: Kedis

    init {
        var address = "127.0.0.1"
        if (PlatformUtils.isWindows) {
            address = "192.168.99.100"
        }
        val server = KedisConfig.ServerOptions(address, 6379)
        val option = KedisConfig.UserOptions("redis", 0)
        kedis = Kedis(KedisConfig(server, option))

        kedis.enable()
        resource = kedis.resource()
    }
}