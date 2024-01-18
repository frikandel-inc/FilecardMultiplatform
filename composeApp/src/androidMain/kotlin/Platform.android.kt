import android.os.Build
import utils.nfc_tag
class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun nfc_id():Long = nfc_tag()

