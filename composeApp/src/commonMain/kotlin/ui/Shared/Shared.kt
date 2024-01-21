package ui.Shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import nfccommunication

suspend fun getNfcId():String {
    var message = ""
    runBlocking {
        withContext(context = Dispatchers.IO) {
            message = nfccommunication().toString()
        }
    }
    return message
}