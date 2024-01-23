package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun HomeAppBar(navigator: Navigator,content: @Composable ()->Unit){
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = {navigator.navigate("/nfc",NavOptions(launchSingleTop = true))}) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                imageVector = Icons.Filled.Nfc,
                                contentDescription = "NFC"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "Scan",
                                style = MaterialTheme.typography.labelSmall)
                        }
                        }
                    TextButton(onClick = { navigator.navigate("/home", NavOptions(launchSingleTop = true))}) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Icon(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "Home",
                                style = MaterialTheme.typography.labelSmall)

                        }
                    }
                    TextButton(onClick = { navigator.navigate("/file", NavOptions(launchSingleTop = true))}) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                imageVector = Icons.Filled.Download,
                                contentDescription = "file"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "File",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    )
    {
        content()
    }
}