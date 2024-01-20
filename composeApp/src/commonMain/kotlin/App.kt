
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.Window

@OptIn(ExperimentalResourceApi::class, FlowPreview::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App(NfcId:Long) {
    MaterialTheme {
        Window(NfcId)
    }
}
