
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, FlowPreview::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App() {
    MaterialTheme {
        ui.Window()
    }
}
