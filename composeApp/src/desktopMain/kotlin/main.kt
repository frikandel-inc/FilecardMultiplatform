
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {  application {
        Window(onCloseRequest = ::exitApplication, title = "Filecard") {
            var NfcId: MutableState<Long> = mutableStateOf(0L)
            launch {
                NfcId =  mutableStateOf(nfc())
            }
            App(NfcId.value)


        }
    }
    }
}
 fun nfc(): Long {
    val nfc = Nfc.NfcCommunication()
    var output: Long = 0L
        output = nfc.getSerial()
    return output
}
@Preview
@Composable
fun AppDesktopPreview() {
    App( 0L )
}