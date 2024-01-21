package serverutil

import io.ktor.utils.io.core.*
import serverutil.FTPFile as File


expect class FtpClient {
    fun connect(host: String, port: Int)
    var implicit: Boolean
    var utf8: Boolean
    var passive: Boolean
    fun login(user: String, password: String)
    val isConnected: Boolean
    var privateData: Boolean
    fun downloadFile(server: String, ftpuser: String, pass: String, remoteFile: String, localFile: String): Boolean
    fun uploadFile(server: String, ftpuser: String, pass: String, localFile: String, remoteFile: String): Boolean
    fun mkdir(path: String): Boolean
    fun deleteFile(path: String): Boolean
    fun deleteDir(path: String): Boolean
    fun rename(old: String, new: String): Boolean
    fun list(path: String?): List<File>
    fun file(path: String): File
    fun exit(): Boolean
}