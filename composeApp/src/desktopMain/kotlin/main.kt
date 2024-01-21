
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {  application {
        Window(onCloseRequest = ::exitApplication, title = "Filecard") {
            var NfcId by mutableStateOf(0L)
//            var nfcId = mutableStateOf(0L)

            var nfcId by remember { mutableStateOf(0L) }

            DisposableEffect(Unit) {
                val job = launch {
                    nfcId = withContext(Dispatchers.IO) {
                        nfc()
                    }
                }

                onDispose {
                    job.cancel()
                }
            }

            App(nfcId)
//            DisposableEffect(Unit) {
//                nfcId.value = withContext(Dispatchers.IO) {
//                    nfc()
//                }
//            }
//            App(nfcId.value)

        }
    }
    }
}
fun nfc(): Long {
    val nfc = Nfc.NfcCommunication()
    return nfc.getSerial()
}
@Preview
@Composable
fun AppDesktopPreview() {
    App(0L)
}