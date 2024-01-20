package serverutil

import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


expect class FTPclient{
    fun downloadFile(server: String, user: String, pass: String, remoteFile: String, localFile: String): Boolean
    fun uploadFile(server: String, user: String, pass: String, localFile: String, remoteFile: String): Boolean
}