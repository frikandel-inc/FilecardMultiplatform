package ui.Expanded

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun HomeAppBarExpanded(content: @Composable ()->Unit) {
    Scaffold() {
        Row {
            HomeNavigationRailExpanded()
            content()
        }
    }
}

@Composable
fun HomeNavigationRailExpanded(){
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Scan")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Nfc)
    NavigationRail {
        Spacer(Modifier.weight(1f))
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }

            )
        }
        Spacer(Modifier.weight(1f))
    }
//    NavigationRail(modifier = Modifier.align(Alignment.CenterVertically)) {
//        Spacer(Modifier.weight(1f))
//        NavigationRailItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.Nfc,
//                    contentDescription = "NFC"
//                )
//            },
//            selected = true,
//            label = { Text("Scan") },
//            onClick = { }
//        )
//
//        NavigationRailItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.Home,
//                    contentDescription = "Home"
//                )
//            },
//            selected = true,
//            label = { Text("Home") },
//            onClick = { }
//        )
//    }
}