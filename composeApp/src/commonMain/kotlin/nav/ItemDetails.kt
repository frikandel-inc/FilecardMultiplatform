package nav

import com.arkivanov.decompose.ComponentContext

interface ItemDetailsComponent {

    // Omitted code

    fun onCloseClicked()
}

class DefaultItemDetailsComponent(
    componentContext: ComponentContext,
    itemId: Long,
    private val onFinished: () -> Unit
) : ItemDetailsComponent, ComponentContext by componentContext {

    // Omitted code

    override fun onCloseClicked() {
        onFinished()
    }
}