package ui.Compact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeAppBarCompact(content: @Composable ()->Unit){
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = {}) {
                        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.Nfc,
                                contentDescription = "NFC"
                            )
                            Text(text = "Scan", style = MaterialTheme.typography.labelSmall)
                        }
                        }
                    TextButton(onClick = {}) {
                        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {

                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                            Text(text = "Home", style = MaterialTheme.typography.labelSmall)

                        }
                    }
                }
            }
        }
    )
    {
        PaddingValues(100.dp)
        content()
    }
}