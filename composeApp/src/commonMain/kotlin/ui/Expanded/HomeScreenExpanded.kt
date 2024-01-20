package ui.Expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenExpanded(NfcId: Long) {
    Row {
        HomeAppBarExpanded()
        HomeMainExpanded(NfcId)
    }
}
@Composable
fun HomeMainExpanded(NfcId: Long){
    Column {
        Text(
            text = "Expanded",
            style = MaterialTheme.typography.titleLarge,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )

        Text(
            text = NfcId.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
    }

}