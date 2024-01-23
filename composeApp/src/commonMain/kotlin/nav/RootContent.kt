package nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.ListChild -> ListContent(component = child.component)
            is RootComponent.Child.DetailsChild -> DetailsContent(component = child.component)
        }
    }
}
@Composable
fun DetailsContent(component: DetailsComponent) {
    TODO("Not yet implemented")
}

@Composable
fun ListContent(component: ListComponent) {

}
