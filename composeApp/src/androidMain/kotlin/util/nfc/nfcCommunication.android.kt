package util.nfc
import com.filecard.multiplatform.readstatus

actual suspend fun nfcCommunication(): Long {
    return readstatus()
}