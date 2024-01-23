package util.ftp

import org.apache.commons.net.ftp.FTPFile
import java.util.*

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

}