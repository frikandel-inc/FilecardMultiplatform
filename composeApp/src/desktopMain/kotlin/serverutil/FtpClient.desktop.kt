package serverutil

import org.apache.commons.net.ftp.FTP
import java.io.FileInputStream
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPCmd
import org.apache.commons.net.ftp.FTPFile
import serverutil.FTPFile as File
import java.io.FileOutputStream

actual class FtpClient{
    private val client: FTPClient = FTPClient().apply {
        autodetectUTF8 = true
    }

    actual fun connect(host: String, port: Int) {
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

    actual fun login(user: String, password: String) {
        client.login(user, password)
        client.setFileType(FTP.BINARY_FILE_TYPE)
        supportsMlsCommands = client.hasFeature(FTPCmd.MLST)
    }

    actual val isConnected: Boolean
        get() = client.isConnected
    actual var privateData: Boolean = false


    actual fun downloadFile(server: String, ftpuser: String, pass: String, remoteFile: String, localFile: String): Boolean{
        try {
            client.setRemoteVerificationEnabled(false)
            client.connect(server, 21)
            client.login(ftpuser, pass)
            client.enterLocalPassiveMode()
            client.setFileType(FTPClient.BINARY_FILE_TYPE)
            val outputStream = FileOutputStream(localFile)
            return  client.retrieveFile(remoteFile, outputStream)
        } finally {
            client.logout()
            client.disconnect()
        }
    }
    
    actual fun uploadFile(server: String, ftpuser: String, pass: String, localFile: String, remoteFile: String): Boolean {
        try {
            client.connect(server, 21)
            client.login(ftpuser, pass)
            client.enterLocalPassiveMode()
            val inputStream = FileInputStream(localFile)
            return client.storeFile(remoteFile, inputStream)
        } finally {
            client.logout()
            client.disconnect()
        }
    }

    actual fun mkdir(path: String): Boolean {
        return client.makeDirectory(path)
    }

    actual fun deleteFile(path: String): Boolean {
        return client.deleteFile(path)
    }

    actual fun deleteDir(path: String): Boolean {
        return client.removeDirectory(path)
    }

    actual fun rename(old: String, new: String): Boolean {
        return client.rename(old, new)
    }

    actual fun list(path: String?): List<File> {
        return convertFiles(if (supportsMlsCommands) client.mlistDir(path) else client.listFiles(path))
    }

    actual fun file(path: String): File {
        if (!supportsMlsCommands) {
            // TODO improve this
            throw IllegalStateException("server does not support MLST command")
        }
        return File(client.mlistFile(path))
    }

    actual fun exit(): Boolean {
        if (!client.logout()) {
            return false
        }
        client.disconnect()
        return true
    }

    companion object {
        internal fun convertFiles(files: Array<FTPFile>): List<File> {
            val result = ArrayList<File>()
            files.forEach {
                result.add(File(it))
            }
            return result
        }
    }


}