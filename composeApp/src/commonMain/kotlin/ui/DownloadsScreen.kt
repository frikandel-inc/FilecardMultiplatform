package ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.ftp.FTPFile
import util.ftp.ftpDownload
import util.ftp.ftpFun

@Composable
fun DownloadScreen(userid:Long?) {
    var filelist by remember { mutableStateOf(arrayListOf<FTPFile>()) }
    val coroutineScope = rememberCoroutineScope()
//    var userid : Long = 1
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                // assign de message value met de main thread, getNfcId wordt nogsteeds
                // gedaan met de IO thread want dat staat in de functie geschreven
                if (userid == null) {
                    println("userid-download:"+userid)
                } else {
                    coroutineScope.launch(Dispatchers.IO) {
                        val ftpfilelist : ArrayList<FTPFile> = ftpFun(userid)
                        withContext(Dispatchers.Default) {
                            filelist = ftpfilelist
                        }
                    }
                }

            }
        ) {
            Text("Connect to File Server")
        }
            Text(
                text = "Results "+filelist.size+" , Id : $userid",
                modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelMedium
            )



        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier =  Modifier.fillMaxWidth().animateContentSize()) {
            for (file in filelist) {
                item {
                    Card {
                        Text(
                            text = file.name,
                            modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = file.size.toString()+" bytes",
                            modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.labelMedium
                        )
                        TextButton(
                            modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                            onClick = {
//                        // assign de message value met de main thread, getNfcId wordt nogsteeds
//                        // gedaan met de IO thread want dat staat in de functie geschreven
                            coroutineScope.launch(Dispatchers.IO) {
                                if (userid != null) {
                                    ftpDownload(file.name, userid)
                                }
                            }
                        })
                        {
                            Text(
                                modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically),
                                text ="Download"
                            )
                        }
                    }
                }

//                Button(
//                    onClick = {
//                        // assign de message value met de main thread, getNfcId wordt nogsteeds
//                        // gedaan met de IO thread want dat staat in de functie geschreven
//                        coroutineScope.launch(Dispatchers.IO) {
//                            ftpDownload(file.name, userid)
//                        }
//                    }
//                ) {
//                    Text(file.name)
//                }
            }
        }

    }
}