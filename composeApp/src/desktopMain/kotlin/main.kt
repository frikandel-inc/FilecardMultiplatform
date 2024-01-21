import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import serverutil.FtpClientJvm

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "FilecardMultiplatform") {
        //maak een FtpClientJvm aan en geef die door naar ftptest
        val ftpclient = FtpClientJvm()
        ftptest(ftpclient)
        App()
    }
}

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
@Preview
@Composable
fun AppDesktopPreview() {
    val ftpclient = FtpClientJvm()
    ftptest(ftpclient)
}