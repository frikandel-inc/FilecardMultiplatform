package util.ftp

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun ftpFun (userid: Long): ArrayList<ftpFile> {
    val counterContext = newSingleThreadContext("CounterContext")
    return withContext(counterContext) {
        val client = FtpClientFactory.create()
        client.utf8 = true
        client.passive = true
        client.connect("92.65.40.77", 3134)
        client.login("administrator", "Bitboysxp1")
        println(client.isConnected)
        val files: ArrayList<ftpFile> = client.list("/$userid/")
        println(files.size)
        client.exit()
        return@withContext files
    }
}
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
expect suspend fun ftpDownload (downloadfile: String, userid: Long)