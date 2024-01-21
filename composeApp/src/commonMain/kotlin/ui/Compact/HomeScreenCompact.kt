package ui.Compact

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreenCompact(NfcId: Long) {
    Column {
        HomeAppBarCompact(
            content = {
            HomeMainCompact(NfcId)
        }
        )

    }

}

@Composable
fun HomeMainCompact(NfcId: Long){
    Column {
        Text(
            text = "Compact",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = NfcId.toString(),
            style = MaterialTheme.typography.titleMedium,
        )
    }

}
