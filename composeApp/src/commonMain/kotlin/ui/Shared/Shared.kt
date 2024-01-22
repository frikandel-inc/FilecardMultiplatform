package ui.Shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nfccommunication

suspend fun getNfcId():String {
    return withContext(context = Dispatchers.IO) {
        nfccommunication().toString()
    }
}