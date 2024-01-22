import android.os.Build
import com.filecard.multiplatform.MainActivity
import com.filecard.multiplatform.readstatus


class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}

actual suspend fun nfccommunication(): Long {
    return readstatus()
}