

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.FlowPreview
import nav.RootComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
@OptIn(ExperimentalResourceApi::class, FlowPreview::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App(root: RootComponent) {
    var colorScheme = lightColorScheme()
    if (isSystemInDarkTheme() == true) {
        colorScheme = darkColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        val childStack by root.stack.subscribeAsState()
        Children(stack = childStack, animation = stackAnimation(slide())) { child ->
            when(val instance = child.instance) {
                is RootComponent.Child.DetailsChild -> RootComponent.Child.DetailsChild(instance.component)
                is RootComponent.Child.ListChild -> RootComponent.Child.ListChild(instance.component)

                else -> {}
            }

        }
//        ui.Window()
    }
}
