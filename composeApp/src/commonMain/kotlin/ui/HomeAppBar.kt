package ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun HomeAppBar(
    navigator: Navigator,
    content: @Composable () -> Unit,
){
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Nfc,
                            contentDescription = "NFC"
                        )
                    },
                    label = {
                        Text(
                            text = "NFC",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onClick = {
                        navigator.navigate("/nfc",NavOptions(launchSingleTop = true))

                    }
                )
                NavigationBarItem(
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text(
                            text = "Home",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onClick = {
                        navigator.navigate("/home",NavOptions(launchSingleTop = true))
                    }
                )
                NavigationBarItem(
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Download,
                            contentDescription = "File"
                        )
                    },
                    label = {
                        Text(
                            text = "File",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onClick = {
                        navigator.navigate("/file",NavOptions(launchSingleTop = true))
                    }
                )
            }
//            BottomAppBar {
//                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
////                    Spacer(modifier = Modifier.padding(16.dp))
//                    TextButton(onClick = {navigator.navigate("/nfc",NavOptions(launchSingleTop = true))}) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Icon(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                imageVector = Icons.Filled.Nfc,
//                                contentDescription = "NFC"
//                            )
//                            Text(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                text = "Scan",
//                                style = MaterialTheme.typography.labelSmall)
//                        }
//                        }
//                    TextButton(onClick = { navigator.navigate("/home", NavOptions(launchSingleTop = true))}) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                            Icon(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                imageVector = Icons.Filled.Home,
//                                contentDescription = "Home"
//                            )
//                            Text(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                text = "Home",
//                                style = MaterialTheme.typography.labelSmall)
//
//                        }
//                    }
//                    TextButton(onClick = { navigator.navigate("/file", NavOptions(launchSingleTop = true))}) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Icon(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                imageVector = Icons.Filled.Download,
//                                contentDescription = "file"
//                            )
//                            Text(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                text = "File",
//                                style = MaterialTheme.typography.labelSmall
//                            )
//                        }
//                    }
//                }
//            }
        }
    )
    {
        content()
    }
}