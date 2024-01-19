package serverutil

import
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*



class Fileclient {
    private val client = HttpClient()
    suspend fun login(): Int {
        val httpclient
    }
}