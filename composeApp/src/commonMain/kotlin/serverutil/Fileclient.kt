package serverutil

import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*



class Fileclient {
    private val client = HttpClient() {
        install(Auth) {
            digest {
                DigestAuthCredentials("user", "password")
            }
        }
    }

    suspend fun login(): Int {
        val response: HttpResponse = client.get("http://localhost:8080/")
        println(response.bodyAsText())
        return response.status.value
    }
}