package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.ftp.ftpDownload
import util.ftp.ftpFun
import util.ftp.ftpFile as ftpFile

@Composable
fun DownloadScreen(userid:Long?) {
    var filelist by remember { mutableStateOf(arrayListOf<ftpFile>()) }
    val coroutineScope = rememberCoroutineScope()
//    var userid : Long = 1
//        val counterContext = newSingleThreadContext("CounterContext")
//        val context = LocalContext.current // WERKT NIET

    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(32.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                // assign het message value met de main thread, getNfcId wordt nog steeds
                // gedaan met de IO-thread want dat staat in de functie geschreven
                if (userid == null) {

                    println("userid-download: $userid")
                } else {
                    coroutineScope.launch(Dispatchers.IO) {
                        val ftpFileList: ArrayList<ftpFile> = ftpFun(userid)
                        withContext(Dispatchers.Default) {
                            filelist = ftpFileList
                        }
                    }
                }
            }
        ) {
            Text("Connect to File Server")
        }
        Text(
            text = "Results " + filelist.size + " , Id : $userid",
            modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelMedium
        )



        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().animateContentSize()
        ) {
            for (file in filelist) {
                file.isDownloaded = file.run { downloaded() }
                val size: String
                if (file.size > 1000000000) {
                    size = "${file.size / 1000} GB"
                } else if (file.size > 1000000) {
                    size = "${file.size / 1000} MB"
                } else if (file.size > 1000) {
                    size = "${file.size / 1000} KB"
                } else {
                    size = "${file.size} B"
                }

                item {
                    var showContent by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.clickable { /* Handle click event here */ }) {
                        Button(onClick = { showContent = !showContent }) { Text(file.name) }
                        AnimatedVisibility(showContent) {
                            Card {
                                Text(
                                    text = file.name,
                                    modifier = Modifier.padding(4.dp)
                                        .align(Alignment.CenterHorizontally),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = size,
                                    modifier = Modifier.padding(2.dp)
                                        .align(Alignment.CenterHorizontally),
                                    style = MaterialTheme.typography.labelMedium
                                )
                                if (file.isDownloaded == true) {
                                    TextButton(
                                        modifier = Modifier.padding(4.dp)
                                            .align(Alignment.CenterHorizontally),
                                        onClick = {
                                            coroutineScope.launch(Dispatchers.IO) {
                                                file.open()
                                            }
                                        })
                                    {
                                        Text(
                                            modifier = Modifier.padding(2.dp)
                                                .align(Alignment.CenterVertically),
                                            text = "Open"
                                        )
                                    }
                                    TextButton(
                                        modifier = Modifier.padding(4.dp)
                                            .align(Alignment.CenterHorizontally),
                                        onClick = {
                                            coroutineScope.launch(Dispatchers.IO) {
                                                file.deletefromdevice()
                                            }
                                        })
                                    {
                                        Text(
                                            modifier = Modifier.padding(2.dp)
                                                .align(Alignment.CenterVertically),
                                            text = "Delete"
                                        )
                                    }
                                } else {
                                    TextButton(
                                        modifier = Modifier.padding(8.dp)
                                            .align(Alignment.CenterHorizontally),
                                        onClick = {
                                            coroutineScope.launch(Dispatchers.IO) {
                                                if (userid != null) {
                                                    ftpDownload(file.name, userid)
                                                }
                                            }
                                        })
                                    {
                                        Text(
                                            modifier = Modifier.padding(4.dp)
                                                .align(Alignment.CenterVertically),
                                            text = "Download"
                                        )
                                    }
                                }
                                TextButton(
                                    modifier = Modifier.padding(8.dp)
                                        .align(Alignment.CenterHorizontally),
                                    onClick = {
                                        coroutineScope.launch(Dispatchers.IO) {
                                            if (userid != null) {
                                                ftpDownload(file.name, userid)
                                            }
                                        }
                                    })
                                {
                                    Text(
                                        modifier = Modifier.padding(4.dp)
                                            .align(Alignment.CenterVertically),
                                        text = "Share"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
