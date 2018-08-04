import com.whalin.MemCached.MemCachedClient
import com.whalin.MemCached.SockIOPool

object MemcachedTest {

    val mcc = MemCachedClient()

    // TODO　ymlから設定読み込んでる
    init {
        var address = "127.0.0.1"
        if (PlatformUtils.isWindows) {
            address = "192.168.99.100"
        }
        val servers: Array<String> = arrayOf(
            "$address:11211"
        )

        val weights: Array<Int> = arrayOf(
                1
        )

        val pool: SockIOPool = SockIOPool.getInstance()
        pool.servers = servers
        pool.weights = weights
        pool.initConn = 5
        pool.minConn = 5
        pool.maxConn = 250
        pool.maxIdle = 1000 * 60 * 6
        pool.initialize()
    }

    /**
     * 下記コマンドでmemcachedサーバーを起動させる
     * docker run --name some-memcached -d -p 11211:11211 memcached
     */
    @JvmStatic fun main(args: Array<String>) {
        mcc.set("key1", "value1")
        println(mcc.get("key1"))
    }
}
