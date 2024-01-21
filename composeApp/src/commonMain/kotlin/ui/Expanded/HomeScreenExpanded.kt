package ui.Expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ui.Shared.getNfcId

@Composable
fun HomeScreenExpanded() {
    Row {
        HomeAppBarExpanded()
            HomeMainExpanded()

    }
}
@Composable
fun HomeMainExpanded(){
    var message by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        message = getNfcId()
    }
    Column {
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
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    message = getNfcId()
                }
            }
        ){
            Text("Button")
        }
    }

}