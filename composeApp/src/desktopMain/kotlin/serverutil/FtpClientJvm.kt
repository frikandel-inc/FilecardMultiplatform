package serverutil

import kotlinx.coroutines.*
import org.apache.commons.net.ftp.FTP
import java.io.FileInputStream
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPCmd
import org.apache.commons.net.ftp.FTPFile
import serverutil.FTPFile as File
import java.io.FileOutputStream

class FtpClientJvm : FtpClientCommon{
    private val client: FTPClient = FTPClient().apply {
        autodetectUTF8 = true
    }

    override suspend fun connect(host: String, port: Int) {
        withContext(Dispatchers.IO) {
            client.connect(host, port)
            client.setRemoteVerificationEnabled(false)
        }
    }

    override var implicit: Boolean = false

    override var utf8: Boolean = false
        set(value) {
            if (value) client.controlEncoding = "UTF-8"
            field = value
        }

    override var passive: Boolean = false
        set(value) {
            if (value) client.enterLocalPassiveMode()
            else client.enterLocalActiveMode()
            field = value
        }

    private var supportsMlsCommands = false

    override suspend fun login(user: String, password: String) {
        withContext(Dispatchers.IO) {
            client.login(user, password)
            client.setFileType(FTP.BINARY_FILE_TYPE)
            supportsMlsCommands = client.hasFeature(FTPCmd.MLST)
        }
    }

    override val isConnected: Boolean
        get() = client.isConnected
    override var privateData: Boolean = false


    override suspend fun downloadFile(remoteFile: String, localFile: String): Boolean {
        withContext(Dispatchers.IO) {
            val outputStream = FileOutputStream(localFile)
            client.logout()
            client.disconnect()
            return client.retrieveFile(remoteFile, outputStream)
        }
    }

    
    override suspend fun uploadFile(localFile: String, remoteFile: String): Boolean {
        withContext(context = Dispatchers.IO) {
            try {
                val inputStream = FileInputStream(localFile)
                return client.storeFile(remoteFile, inputStream)
            } finally {
                client.logout()
                client.disconnect()
            }
        }
    }

    override suspend fun mkdir(path: String): Boolean {
        withContext(Dispatchers.IO) {
            return client.makeDirectory(path)
        }
    }

    override suspend fun deleteFile(path: String): Boolean {
        withContext(Dispatchers.IO) {
            return client.deleteFile(path)
        }
    }

    override suspend fun deleteDir(path: String): Boolean {
        withContext(Dispatchers.IO) {
        return client.removeDirectory(path)
            }
    }

    override suspend fun rename(old: String, new: String): Boolean {
        return client.rename(old, new)
    }

    override suspend fun list(path: String?): List<File> {
        return convertFiles(if (supportsMlsCommands) client.mlistDir(path) else client.listFiles(path))
    }

    override suspend fun file(path: String): File {
        if (!supportsMlsCommands) {
            // TODO improve this
            throw IllegalStateException("server does not support MLST command")
        }
        return File(client.mlistFile(path))
    }

    override suspend fun exit(): Boolean {
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