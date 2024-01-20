package ui.Expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun HomeAppBarExpanded(){
    NavigationRail {
        Column(Modifier.align(Alignment.CenterHorizontally)) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Nfc,
                        contentDescription = "NFC"
                    )
                },
                selected = true,
                label = { Text("Scan") },
                onClick = {  }
            )
        }
        NavigationRailItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            selected = true,
            label = { Text("Home") },
            onClick = {  }
        )
    }
}
