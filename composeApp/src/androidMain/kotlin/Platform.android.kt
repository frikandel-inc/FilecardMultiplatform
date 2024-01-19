import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.os.Build
import com.filecard.multiplatform.MainActivity
import com.romellfudi.fudinfc.util.async.Nfc
import com.romellfudi.fudinfc.util.interfaces.NfcReadUtility
import com.romellfudi.fudinfc.util.sync.NfcReadUtilityImpl
import javax.security.auth.callback.Callback

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}

private class AndroidNfcManager(): NfcInterface {


    override fun listen(data: String,callback: (String) -> Unit) {
        return callback(data)
    }
    override fun quit():String {
        return "quit"
    }
}

actual fun CommenNfcManager(): NfcInterface = AndroidNfcManager()