package util.ftp

import util.ftp.FTPFile as File


expect class FtpClient {
    suspend fun connect(host: String, port: Int)
    var implicit: Boolean
    var utf8: Boolean
    var passive: Boolean
    suspend fun login(user: String, password: String)
    val isConnected: Boolean
    var privateData: Boolean
    suspend fun downloadFile(remoteFile: String, localFile: String): Boolean
    suspend fun uploadFile(localFile: String, remoteFile: String): Boolean
    suspend fun mkdir(path: String): Boolean
    suspend fun deleteFile(path: String): Boolean
    suspend fun deleteDir(path: String): Boolean
    suspend fun rename(old: String, new: String): Boolean
    suspend fun list(path: String?): List<File>
    suspend fun file(path: String): File
    suspend fun exit(): Boolean
}