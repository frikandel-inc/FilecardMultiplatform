package util.ftp

import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun ftpFun (): List<FTPFile> {
    var message : String = ""
    val counterContext = newSingleThreadContext("CounterContext")
    return withContext(counterContext) {
        val client = FtpClientFactory.create()
        client.utf8 = true
        client.passive = true
        client.connect("92.65.40.77", 3134)
        client.login("administrator", "Bitboysxp1")
        val files: List<FTPFile> = client.list("/1/")
        if (files.size > 1) {
            //geef hier aan welke file hij moet downloaden uit de lijst, mits de directory niet leeg is
            val downloadfile: String = files[5].name
            val path = System.getProperty("user.dir")
            //Maak een directory genaamd .downloads aan in /composeApp/, anders crasht de app haha
            client.downloadFile(
                "/1/$downloadfile",
                "$path/.downloads/$downloadfile"
            )
        }
        client.exit()
        return@withContext files
    }

}
    // nog implementeren
