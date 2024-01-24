package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.ftp.ftpFun
import util.ftp.FTPFile

@Composable
fun DownloadScreen() {
    var ftpmessage by remember { mutableStateOf(List<FTPFile>) }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Text(text = "Downloads")
        Text(
            text = ftpmessage,
            style = MaterialTheme.typography.titleMedium,
        )
        Button(
            onClick = {
                //coroutine wordt gedaan met rememberCoroutineScope om memory leaks tegen te gaan
                // wordt gedeclareerd op line 25
                var ftpfilelist : List<FTPFile> =
                    // assign de message value met de main thread, getNfcId wordt nogsteeds
                    // gedaan met de IO thread want dat staat in de functie geschreven
                    withContext(Dispatchers.IO){
                        return@withContext ftpfilelist = ftpFun()
                    }
                    println(ftpmessage)
                }

            }
        ){
            Text("Button 2")
        }
    }
}
