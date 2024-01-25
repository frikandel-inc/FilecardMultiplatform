package util.ftp

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import org.apache.commons.net.ftp.FTPFile
import java.util.*
import splitties.init.appCtx

actual class FTPFile(private val file: FTPFile){
    actual val name: String
        get() = file.name
    actual val size: Long
        get() = file.size
    actual val user: String
        get() = file.user
    actual val group: String
        get() = file.group
    actual val timestamp: Calendar
        get() = file.timestamp
    actual val isDirectory: Boolean
        get() = file.isDirectory
    actual val isFile: Boolean
        get() = file.isFile
    actual val isSymbolicLink: Boolean
        get() = file.isSymbolicLink
    actual val isUnknown: Boolean
        get() = file.isUnknown
    actual val link: String?
        get() = file.link
    actual var isDownloaded: Boolean? = null
    actual fun downloaded(): Boolean {
        appCtx.fileList().forEach {
            if (it == this.name) {
                return true
            }
        }
        return false
    }
    actual fun open() {
        val intent = Intent(Intent.ACTION_SEND)
        val chooser = Intent.createChooser(intent, /* title */ null)
        try {
            startActivity(appCtx, chooser, null)
        } catch (e: ActivityNotFoundException) {
            // Define what your app should do if no activity can handle the intent.
        }

    }
    actual fun deletefromdevice() {

    }

}