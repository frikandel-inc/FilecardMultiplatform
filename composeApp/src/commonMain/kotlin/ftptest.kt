import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import serverutil.FtpClientCommon as FtpClient
import serverutil.FTPFile as File
import kotlinx.coroutines.*
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalResourceApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ftptest(ftpClient: FtpClient){
    MaterialTheme {
        val scopeio = CoroutineScope(Dispatchers.IO)
        val counterContext = newSingleThreadContext("CounterContext")
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Button(onClick = {showContent = !showContent}) { Text("FTP test") }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    LaunchedEffect(Unit) {
                        withContext(counterContext) {
                            try {
                                ftpClient.utf8 = true
                                ftpClient.passive = true
                                ftpClient.connect("92.65.40.77", 3134)
                                ftpClient.login("administrator", "Bitboysxp1")
                                val files: List<File> = ftpClient.list("/1/")
                                if (files.size > 1) {
                                    println("files lijst is niet leeg!")
                                }
                                for (file in files) {
                                    println(file.name)
                                }
                                if (files.size > 1) {
                                    //geef hier aan welke file hij moet downloaden uit de lijst, mits de directory niet leeg is
                                    val downloadfile: String = files[5].name
                                    val path = System.getProperty("user.dir")
                                    //Maak een directory genaamd .downloads aan in /composeApp/, anders crasht de app haha
                                    ftpClient.downloadFile(
                                        "/1/$downloadfile",
                                        "$path/.downloads/$downloadfile"
                                    )
                                }
                                ftpClient.exit()
                            } catch (e: Exception) {
                                println(e)
                            }
                        }
                    }
                    //print een lijstje uit van alle files
                    Text(ftpClient.isConnected.toString())
//                    var files: List<File> = mutableListOf()
//                    files.forEach { file ->
//                        Text(file.name)
//                    }
//                    if (files.size > 1) {
//                        //geef hier aan welke file hij moet downloaden uit de lijst, mits de directory niet leeg is
//                        val downloadfile: String = files[5].name
//                        val path = System.getProperty("user.dir")
//                        //Maak een directory genaamd .downloads aan in /composeApp/, anders crasht de app haha
//                        scopeio.launch {
//                            ftpClient.downloadFile(
//                                "/1/$downloadfile",
//                                "$path/.downloads/$downloadfile"
//                            )
//                        }
//                        Text(" ")
//
//                        Text("$downloadfile downloaded!")
//                    } else {
//                        Text("werkt niet!")
//                    }
                }
            }
        }
    }
}
