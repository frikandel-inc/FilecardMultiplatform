package nav

sealed interface ScreenAEvent {
    data object ClickButtonA: ScreenAEvent
    data class UpdateText(val text: String): ScreenAEvent
}