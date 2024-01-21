package ui.Compact

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.Shared.getNfcId

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
            onClick = { message = getNfcId() }
        ){
            Text("Button")
        }
    }

}
