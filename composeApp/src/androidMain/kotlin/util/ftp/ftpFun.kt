package util.ftp

import android.R
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
import java.io.OutputStream


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
        val path = System.getProperty("user.dir")
        //Maak een directory genaamd /.downloads/ aan in /composeApp/

//        val dirpath = Context.getFilesDir("$path/.downloads/")
//        if (!Files.exists(dirpath)) {
//            try {
//                Files.createDirectories(dirpath)
//                println("Directory created")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } else {
//            println("Directory already exists")
//        }

        client.downloadFile(
            "/$userid/$downloadfile",
            "$path/.downloads/$downloadfile"
        )
        client.exit()
    }
}



//actual suspend fun downloadFile(context: Context, remoteFile: String, localFileName: String): Boolean {
//    // Prepare content values.
//    val resolver = context.contentResolver
//    val contentValues = ContentValues().apply {
//        put(MediaStore.Downloads.DISPLAY_NAME, localFileName)
//        put(MediaStore.Downloads.MIME_TYPE, "application/octet-stream")
//        put(MediaStore.Downloads.RELATIVE_PATH, "Download/")
//    }
//
//    // Get uri from MediaStore
//    val fileUri: Uri? = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
//
//    // Prepare to write to the file.
//    var outStream: OutputStream? = null
//    if (fileUri != null) {
//        outStream = resolver.openOutputStream(fileUri)
//    }
//
//    return if (outStream != null) {
//        client.retrieveFile(remoteFile, outStream)
//    } else {
//        false
//    }
//}