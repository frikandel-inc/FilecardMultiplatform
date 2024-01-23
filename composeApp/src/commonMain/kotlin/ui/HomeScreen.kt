package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import util.nfc.getNfcId

@Composable
fun HomeScreen() {
        HomeAppBar(
            content = {
                HomeMain()
            }
        )
}

@Composable
fun HomeMain(){
    var message by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column (modifier = androidx.compose.ui.Modifier.padding(16.dp)){
        Text(
            text = "Compact",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = message,
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
                        message = getNfcId()
                    }
                    println(message)
                }

            }
        ){
            Text("Button")
        }
    }

}

