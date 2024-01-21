package ui.Compact

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nfccommunication

@Composable
fun HomeScreenCompact() {
    Column {
        HomeAppBarCompact(
            content = {
            HomeMainCompact()
        }
        )

    }

}

@Composable
fun HomeMainCompact(){
    var message by remember { mutableStateOf("") }
    LaunchedEffect(key1 ="fetchNfcId") {
        // Assuming nfccommunication() returns a String
        message = nfccommunication().toString()
    }
    Column {
        Text(
            text = "Compact",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
        )

        Button(
            onClick = { /*TODO*/ }
        ){
            Text("Button")
        }
    }

}
