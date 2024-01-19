import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.filecard.multiplatform.MainActivity
import java.security.AccessController.getContext


class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}

private class AndroidNfcManager(): NfcInterface {


    override fun listen() {


    }
    override fun quit():String {
        return "quit"
    }
}

actual fun CommenNfcManager(): NfcInterface = AndroidNfcManager()