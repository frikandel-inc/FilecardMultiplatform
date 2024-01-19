class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

private class AndroidNfcManager(): NfcInterface {


    override fun listen() {

    }
    override fun quit():String {
        return "quit"
    }
}

actual fun CommenNfcManager(): NfcInterface = AndroidNfcManager()