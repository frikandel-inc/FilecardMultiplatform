package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.ftp.ftpFun
import util.ftp.FTPFile
import util.ftp.ftpDownload

@Composable
fun DownloadScreen() {
    var filelist by remember { mutableStateOf(listOf<FTPFile>()) }
    val coroutineScope = rememberCoroutineScope()
    var userid : Long = 1
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Button(
            onClick = {
                // assign de message value met de main thread, getNfcId wordt nogsteeds
                // gedaan met de IO thread want dat staat in de functie geschreven
                coroutineScope.launch(Dispatchers.IO) {
                    val ftpfilelist : List<FTPFile> = ftpFun(userid)
                    withContext(Dispatchers.Default) {
                        filelist = ftpfilelist
                    }
                }
            }
        ) {
            Text("Connect to File Server")
        }
        for (file in filelist) {
            Button(
                onClick = {
                    // assign de message value met de main thread, getNfcId wordt nogsteeds
                    // gedaan met de IO thread want dat staat in de functie geschreven
                    coroutineScope.launch(Dispatchers.IO) {
                        ftpDownload(file.name, userid)
                    }
                }
            ) {
                Text(file.name)
            }
        }
    }
}