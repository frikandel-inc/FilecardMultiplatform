interface Platform {
    val name: String
}
interface NfcInterface {
    fun listen(data: String,callback: (String) -> Unit)
    fun quit():String
}

expect fun getPlatform(): Platform
expect fun CommenNfcManager():NfcInterface

