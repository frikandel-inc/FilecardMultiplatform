package util.nfc

import com.fazecast.jSerialComm.SerialPort
import kotlinx.coroutines.*
import java.nio.charset.StandardCharsets

actual suspend fun nfcCommunication(): Long = coroutineScope {
    val nfc = NfcComm()
    val nfcId = nfc.getSerial()
    return@coroutineScope nfcId
}

class NfcComm {
    suspend fun getSerial(): Long {
        var serialNumber: Long = 0
        val comPort = SerialPort.getCommPorts().firstOrNull() ?: throw RuntimeException("No COM ports available")
        
        try {
            comPort.setBaudRate(115200)
            comPort.openPort()
            println("Serial port opened")
            while (serialNumber == 0L) {
                val availableBytes = comPort.bytesAvailable()
                if (availableBytes > 0) {
                    val readBuffer = ByteArray(comPort.bytesAvailable())
                    comPort.readBytes(readBuffer, readBuffer.size)
                    val message = String(readBuffer, StandardCharsets.US_ASCII).trim { it <= ' ' }
                    if (message != "Provide a Filecard") {
                        println(message)
                        serialNumber = message.toLong()
                    }


                    // Call your function or perform actions based on the received message
                    //if (message.startsWith("Serienummer: ")) {
                    //  serialNumber = Integer.parseInt(message);
                    // Call your function or perform actions based on the serial number
                    //}
                }
                delay(100) // Add a short delay to avoid busy-waiting
                if (serialNumber == 0L){break}
                println("geen data ontvangen dus we sluiten de port")
            }
            
        } catch (e: Exception) {
            println("Error detected: ${e.message}")
        } finally {
            comPort.closePort()
            println("Serial port closed")
        }
        return serialNumber
    }
}