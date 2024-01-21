package ui.Medium

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import ui.Expanded.HomeScreenExpanded

@Composable
fun HomeScreenMedium(NfcId: Long) {
    Row {
        HomeScreenExpanded({ NfcId })
    }
}
