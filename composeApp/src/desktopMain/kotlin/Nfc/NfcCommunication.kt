package Nfc

import com.fazecast.jSerialComm.SerialPort
import java.nio.charset.StandardCharsets

class NfcCommunication {
    fun getSerial(): Long {
        var serialNumber: Long = 0
        var comPort: SerialPort = SerialPort.getCommPort("COM3")
        try {
            comPort = SerialPort.getCommPorts()[0]
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            comPort.setBaudRate(115200)
            comPort.openPort()
            println("Serial port opened")
            while (serialNumber == 0L) {
                if (comPort.bytesAvailable() > 0) {
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
                Thread.sleep(100) // Add a short delay to avoid busy-waiting
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            comPort.closePort()
            println("Serial port closed")
        }
        return serialNumber
    }
    fun getSerialFake(): Long {
        return 0L
    }
}