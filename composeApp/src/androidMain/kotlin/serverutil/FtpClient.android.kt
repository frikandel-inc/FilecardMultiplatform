package serverutil

import java.io.FileInputStream
import org.apache.commons.net.ftp.FTPClient
import java.io.FileOutputStream

actual class FtpClient {
    actual fun downloadFile(server: String, user: String, pass: String, remoteFile: String, localFile: String): Boolean {
        val ftpClient = FTPClient()
        try {
            ftpClient.connect(server)
            ftpClient.login(user, pass)
            val outputStream = FileOutputStream(localFile)
            return ftpClient.retrieveFile(remoteFile, outputStream)
        } finally {
            ftpClient.logout()
            ftpClient.disconnect()
        }
    }

    actual fun uploadFile(server: String, user: String, localFile: String, remoteFile: String): Boolean {
        val ftpClient = FTPClient()
        try {
            ftpClient.connect(server, 21)
            ftpClient.login(user, pass)
            ftpClient.enterLocalPassiveMode() 
            val inputStream = FileInputStream(localFile)
            return ftpClient.storeFile(remoteFile, inputStream) 
        } finally {
            ftpClient.logout()
            ftpClient.disconnect()
        }
    }
}