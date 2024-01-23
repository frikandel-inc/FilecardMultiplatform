package nav

import com.arkivanov.decompose.ComponentContext


interface ItemListComponent {

    // Omitted code

    fun onItemClicked(id: Long)
}

class DefaultItemListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (id: Long) -> Unit
) : ItemListComponent, ComponentContext by componentContext {

    // Omitted code

    override fun onItemClicked(id: Long) {
        onItemSelected(id)
    }
}