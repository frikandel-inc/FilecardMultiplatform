interface Platform {
    val name: String
}
expect fun getPlatform(): Platform

expect suspend fun nfccommunication(): Long
