package util.ftp

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import java.io.*
import java.nio.file.Files
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import io.ktor.utils.io.core.*
import splitties.init.appCtx
import java.io.OutputStream
import kotlin.io.use


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
actual suspend fun ftpDownload(downloadfile: String, userid: Long) {
    val counterContext = newSingleThreadContext("CounterContext")
    return withContext(counterContext) {
        val client = FtpClientFactory.create()
        client.utf8 = true
        client.passive = true
        client.connect("92.65.40.77", 3134)
        client.login("administrator", "Bitboysxp1")

        //download het bestand eerst naar een filestream, vervolgens geven we deze filestream
        //aan big daddy android om ons bestandje op te slaan
        val fileContents = client.downloadFileStream("/$userid/$downloadfile").use {
            it.readBytes()
        }
        appCtx.openFileOutput(downloadfile, Context.MODE_PRIVATE).use {
            it.write(fileContents)
        }
        println("BOMBOCLAAAT")

        client.exit()
    }
}