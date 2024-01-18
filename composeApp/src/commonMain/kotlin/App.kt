
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


@OptIn(ExperimentalResourceApi::class, FlowPreview::class)
@Composable
fun App() {
    MaterialTheme {
        val windowSizeClass = calculateWindowSizeClass(this)

        layout.mainWindow(windowSizeClass)
    }
}