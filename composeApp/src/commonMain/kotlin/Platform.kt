

interface Platform {
    val name: String
}
interface NfcInterface {
    fun listen()
    fun quit():String
}

expect fun getPlatform(): Platform
expect fun CommenNfcManager():NfcInterface

