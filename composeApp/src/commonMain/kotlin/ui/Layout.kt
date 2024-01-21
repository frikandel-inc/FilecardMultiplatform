package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview


/**
 * @param modifier Modifier to be applied to the layout.
 * @author dont use this please
 */









@Composable
@FlowPreview
fun landingPage() {
    Column {
        Row(modifier = Modifier.weight(0.1F).background(Color.Green).fillMaxWidth(1F).align(Alignment.End)) {
            var count by remember { mutableStateOf(0) }
            Text(text="shit")
            Button(onClick = { count++ }) {

                Text("number: ${count}")
            }
        }
        Row(modifier = Modifier.weight(0.9F).background(Color.Red).fillMaxWidth(1F)) {
            Text(text="shit2", Modifier)

        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        HomeSection(title = "title") {
            Text("test")
        }
        Spacer(Modifier.height(16.dp))

    }
}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
//        items(favoriteCollectionsData()) { item ->
//            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
//        }
    }
}
@Composable
fun favoriteCollectionsData():Int {
    return 1;
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
    ) {
        Column(
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text("test")
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text("test2")
                },
                selected = false,
                onClick = {}
            )
        }
    }
}
@Composable
fun MySootheAppLandscape() {
        Row {
            SootheNavigationRail()
            HomeScreen()
        }

}

@Composable
fun MySootheAppPortrait() {
        Scaffold(
            bottomBar = { bottemBar() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }

}

@Composable
fun FavoriteCollectionCard(
    drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
@FlowPreview
fun bottemBar() {
    NavigationBar(
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Nfc,"nfc") },
            label = {
                Text(
                    text = "test"
                )
            },
            selected = true,
            onClick = {}
        )
    }
}
@Composable
fun topBar() {
}

