
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import javax.swing.SwingUtilities

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "Filecard") {
            App()
        }
    }
}

//@Preview
//@Composable
//fun AppDesktopPreview() {
//    App()
//}


fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}