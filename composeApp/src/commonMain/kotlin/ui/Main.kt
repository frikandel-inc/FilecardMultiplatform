package ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import ui.Compact.HomeScreenCompact
import ui.Expanded.HomeScreenExpanded

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@FlowPreview
fun Window(NfcId: () -> Long) {
    val windowSize = calculateWindowSizeClass().widthSizeClass
    if(windowSize.toString() == WindowWidthSizeClass.Expanded.toString()) {
        HomeScreenExpanded(NfcId)
    }
    if ( windowSize.toString() == WindowWidthSizeClass.Medium.toString()) {
        HomeScreenExpanded(NfcId)
    }
    if ( windowSize.toString() == WindowWidthSizeClass.Compact.toString()) {
        HomeScreenCompact(NfcId)
    }
}