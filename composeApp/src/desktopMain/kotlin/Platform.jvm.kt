class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual suspend fun nfccommunication(): Long {
    val nfc = Nfc.NfcCommunication()
    return nfc.getSerial()
}