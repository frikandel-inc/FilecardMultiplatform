package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import util.nfc.getNfcId

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun NfcScreen(nfcId:Long?):Long? {
    var nfcId2 by remember { mutableStateOf(nfcId) }
    var message by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column (modifier = Modifier.padding(16.dp).fillMaxWidth()){
        Spacer(modifier = Modifier.size(60.dp))
        Icon(
            imageVector = Icons.Filled.Nfc,
            contentDescription = "NFC",
            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally)
            )
        Spacer(modifier = Modifier.size(60.dp))
        Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Plaats de Nfc tag op de achterkant van uw telefoon",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "En wacht tot dat hij een trilling geeft",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                text = message,
                style = MaterialTheme.typography.titleMedium,
            )


            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),

                onClick = {
                    //coroutine wordt gedaan met rememberCoroutineScope om memory leaks tegen te gaan
                    // wordt gedeclareerd op line 25
                    coroutineScope.launch {
                        // assign de message value met de main thread, getNfcId wordt nogsteeds
                        // gedaan met de IO thread want dat staat in de functie geschreven
                        withContext(Dispatchers.IO){
                            message = getNfcId()
                        }
                        println("message: "+message)
                        if ((message == "") || (message == "0")) {
                            nfcId2 = null
                        } else {
                            nfcId2 = message.toLong()
                        }
                    }


                }
            ){
                Text("Scan NFC")
            }
        }

    }
    println("nfcId-nfc: "+nfcId2)
    return nfcId2
}