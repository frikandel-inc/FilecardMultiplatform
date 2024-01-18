import android.os.Build
import utils.nfc_tag
class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}
actual fun nfc_id():Long {
    return nfc_tag()
}

