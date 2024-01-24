package util.ftp

import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPCmd
import java.io.FileInputStream
import java.io.FileOutputStream


actual class FtpClient {
    private val client: FTPClient = FTPClient().apply {
        autodetectUTF8 = true
    }

    actual suspend fun connect(host: String, port: Int) {
        client.isRemoteVerificationEnabled = false
        client.connect(host, port)
    }

    actual var implicit: Boolean = false

    actual var utf8: Boolean = false
        set(value) {
            if (value) client.controlEncoding = "UTF-8"
            field = value
        }

    actual var passive: Boolean = false
        set(value) {
            if (value) client.enterLocalPassiveMode()
            else client.enterLocalActiveMode()
            field = value
        }

    private var supportsMlsCommands = false

    actual suspend fun login(user: String, password: String) {
        client.login(user, password)
        client.setFileType(FTP.BINARY_FILE_TYPE)
        supportsMlsCommands = client.hasFeature(FTPCmd.MLST)
    }

    actual val isConnected: Boolean
        get() = client.isConnected

    actual var privateData: Boolean = false

    actual suspend fun downloadFile(remoteFile: String, localFile: String): Boolean {
        val outputStream = FileOutputStream(localFile)
        return client.retrieveFile(remoteFile, outputStream)

    }

    actual suspend fun uploadFile(localFile: String, remoteFile: String): Boolean {
        try {
            val inputStream = FileInputStream(localFile)
            return client.storeFile(remoteFile, inputStream)
        } finally {
            client.logout()
            client.disconnect()
        }
    }

    actual suspend fun mkdir(path: String): Boolean {
        return client.makeDirectory(path)
    }

    actual suspend fun deleteFile(path: String): Boolean {
        return client.deleteFile(path)
    }

    actual suspend fun deleteDir(path: String): Boolean {
        return client.removeDirectory(path)
    }

    actual suspend fun rename(old: String, new: String): Boolean {
        return client.rename(old, new)
    }

    actual suspend fun list(path: String?): ArrayList<FTPFile> {
        return convertFiles(if (supportsMlsCommands) client.mlistDir(path) else client.listFiles(path))
    }

    actual suspend fun file(path: String): FTPFile {
        if (!supportsMlsCommands) {
            // TODO improve this
            throw IllegalStateException("server does not support MLST command")
        }
        return FTPFile(client.mlistFile(path))
    }

    actual suspend fun exit(): Boolean {
        if (!client.logout()) {
            return false
        }
        client.disconnect()
        return true
    }

    companion object {
        internal fun convertFiles(files: Array<org.apache.commons.net.ftp.FTPFile>): ArrayList<FTPFile> {
            val result = ArrayList<FTPFile>()
            files.forEach {
                result.add(FTPFile(it))
            }
            return result
        }
    }
}

actual object FtpClientFactory {
    //maakt een FtpClient aan stuurt hem terug naar de shared code

    actual fun create(): FtpClient {
        return FtpClient()
    }
}