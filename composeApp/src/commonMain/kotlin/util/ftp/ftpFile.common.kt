package util.ftp

import java.util.*

expect class FTPFile {
    val name: String
    val size: Long
    val user: String
    val group: String
    val timestamp: Calendar
    val isDirectory: Boolean
    val isFile: Boolean
    val isSymbolicLink: Boolean
    val isUnknown: Boolean
    val link: String?
    var isDownloaded: Boolean?
    fun downloaded(): Boolean
    fun open()
    fun deletefromdevice()
}