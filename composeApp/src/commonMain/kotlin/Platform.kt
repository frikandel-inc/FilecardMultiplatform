interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun nfc_id(): Long