package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import util.ftp.ftpFun
import util.nfc.getNfcId

@Composable
fun HomeScreen(){
//    val coroutineScope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }
    var ftpmessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column (modifier = Modifier.padding(16.dp).fillMaxWidth()){
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "\nWelkom bij de filcard app",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Wilt u scannen druk op Scan aan de onderkant van uw scherm",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Wilt u uw downloads zien druk dan op Downloads aan de onderkant van uw scherm",
            style = MaterialTheme.typography.titleMedium,
        )
//dit is nog mijn oude spul
        Button(
            onClick = {
                //coroutine wordt gedaan met rememberCoroutineScope om memory leaks tegen te gaan
                // wordt gedeclareerd op line 25
                coroutineScope.launch {
                    // assign de message value met de main thread, getNfcId wordt nogsteeds
                    // gedaan met de IO thread want dat staat in de functie geschreven
                    withContext(Dispatchers.IO){
                        message = getNfcId()
                    }
                    println(message)
                }

            }
        ){
            Text("Button")
        }
        Text(
            text = ftpmessage,
            style = MaterialTheme.typography.titleMedium,
            )
        Button(
            onClick = {
                //coroutine wordt gedaan met rememberCoroutineScope om memory leaks tegen te gaan
                // wordt gedeclareerd op line 25
                coroutineScope.launch {
                    // assign de message value met de main thread, getNfcId wordt nogsteeds
                    // gedaan met de IO thread want dat staat in de functie geschreven
                    withContext(Dispatchers.IO){
                        ftpmessage = ftpFun()
                    }
                    println(ftpmessage)
                }

            }
        ){
            Text("Button 2")
        }
    }

}

