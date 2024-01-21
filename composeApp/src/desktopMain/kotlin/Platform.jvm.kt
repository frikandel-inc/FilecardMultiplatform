import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual suspend fun nfccommunication(): Long {
    var nfcId: Long = 0L
    coroutineScope {
        val nfc = Nfc.NfcCommunication()
        nfcId = nfc.getSerial()
        return@coroutineScope
    }
    return nfcId

}