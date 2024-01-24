package util.ftp

import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Paths

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun ftpFun (userid: Long): ArrayList<FTPFile> {
    var message : String = ""
    val counterContext = newSingleThreadContext("CounterContext")
    return withContext(counterContext) {
        val client = FtpClientFactory.create()
        client.utf8 = true
        client.passive = true
        client.connect("92.65.40.77", 3134)
        client.login("administrator", "Bitboysxp1")
        println(client.isConnected)
        val files: ArrayList<FTPFile> = client.list("/$userid/")
        println(files[5].name)
        client.exit()
        return@withContext files
    }

}
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun ftpDownload (downloadfile: String, userid: Long) {
    val counterContext = newSingleThreadContext("CounterContext")
    return withContext(counterContext) {
        val client = FtpClientFactory.create()
        client.utf8 = true
        client.passive = true
        client.connect("92.65.40.77", 3134)
        client.login("administrator", "Bitboysxp1")
        val path = System.getProperty("user.dir")
        //Maak een directory genaamd .downloads aan in /composeApp/

        val dirpath = Paths.get("$path/.downloads/")

        if (!Files.exists(dirpath)) {
            try {
                Files.createDirectories(dirpath)
                println("Directory created")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("Directory already exists")
        }

        client.downloadFile(
            "/$userid/$downloadfile",
            "$path/.downloads/$downloadfile"
        )
        client.exit()
    }
}