object PlatformUtils {

    private val OS_NAME = System.getProperty("os.name").toLowerCase()

    val isLinux: Boolean
        get() = OS_NAME.startsWith("linux")

    val isMac: Boolean
        get() = OS_NAME.startsWith("mac")

    val isWindows: Boolean
        get() = OS_NAME.startsWith("windows")

    val isSunOS: Boolean
        get() = OS_NAME.startsWith("sunos")
}
