import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import util.ftp.FtpClientJvm

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "Filecard") {
            //maak een FtpClientJvm aan en geef die door naar ftptest
            val ftpclient = FtpClientJvm()
            App()
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
@Preview
@Composable
fun AppDesktopPreview() {
    val ftpclient = FtpClientJvm()
    App()
}