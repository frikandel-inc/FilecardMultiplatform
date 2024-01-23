package ui.Expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.nfc.getNfcId

@Composable
fun HomeScreenExpanded() {
        HomeAppBarExpanded(
            content = {
                HomeMainExpanded()
            }
        )
}
@Composable
fun HomeMainExpanded(){
    var message by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column (modifier = androidx.compose.ui.Modifier.padding(16.dp)){
        Text(
            text = "Expanded",
            style = MaterialTheme.typography.titleLarge,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
        TextButton(
            onClick = {
                //coroutine wordt gedaan met rememberCoroutineScope om memory leaks tegen te gaan
                // wordt gedeclareerd op line 25
                coroutineScope.launch {
                    // assign de message value met de main thread, getNfcId wordt nogsteeds
                    // gedaan met de IO thread want dat staat in de functie geschreven
                    withContext(Dispatchers.IO){
                    message = getNfcId()
                    }
//                    println(message)
                }
//                println(message)
            }
        ) {
            Text("Scan NFC")
        }

    }

}