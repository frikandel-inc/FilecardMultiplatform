package util.nfc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getNfcId():String {
    return withContext(context = Dispatchers.IO) {
        nfcCommunication().toString()
    }
}