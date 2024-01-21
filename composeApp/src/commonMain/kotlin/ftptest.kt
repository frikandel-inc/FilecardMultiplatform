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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ftptest(ftpClient: FtpClient) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val greeting = remember { Greeting().greet() }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Button(onClick = { showContent = !showContent }) {
                Text("FTP test")
            }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    ftpClient.connect("filecard.lennfun.nl", 3134)
                    ftpClient.login("administrator", "Bitboysxp1")
                    Text("FTP verbonden: " + (ftpClient.isConnected))
                    val files: List<File> = ftpClient.list("/1/")
                    //print een lijstje uit van alle  iser
                    files.forEach { file ->
                        Text(file.name)
                    }
                    if (files.size > 1) {
                        //geef hier aan welke file hij moet downloaden uit de lijst, mits de directory niet leeg is
                        val downloadfile : String = files[5].name
                        val Path = System.getProperty("user.dir")
                        //Maak een directory genaamd .downloads aan in /composeApp/, anders crasht de app haha
                        ftpClient.downloadFile("/1/$downloadfile",
                                               "$Path/.downloads/$downloadfile"
                        )
                        Text(" ")
                        
                        Text(downloadfile + " downloaded!")
                    } else {
                        Text("werkt niet!")
                    }
                }
            }
        }
    }
}