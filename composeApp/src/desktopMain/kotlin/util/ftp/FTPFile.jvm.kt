package util.ftp

import kotlinx.io.files.Path
import org.apache.commons.net.ftp.FTPFile
import java.io.File
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.SimpleFileVisitor
import java.util.*
import kotlin.io.FileTreeWalk
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.walk

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
    @OptIn(ExperimentalPathApi::class)
    actual fun downloaded(): Boolean {
        val path = System.getProperty("user.dir")
        //Maak een directory genaamd .downloads aan in /composeApp/
        val localFileList = ArrayList<String>()
        File("$path/.downloads/").walkTopDown().forEach { localFileList.add(it.name) }
        for (filename in localFileList) {
            println(filename)
            if (filename == this.name) {
                return true
            }
        }
        return false
    }
    actual fun open() {

    }
    actual fun deletefromdevice() {

    }

}
