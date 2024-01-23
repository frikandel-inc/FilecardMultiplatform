

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import org.jetbrains.compose.resources.ExperimentalResourceApi
@OptIn(ExperimentalResourceApi::class, FlowPreview::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App() {
    var colorScheme = lightColorScheme()
    if (isSystemInDarkTheme() == true) {
        colorScheme = darkColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
//        val childStack by root.childStack.subscribeAsState()
//        Children(stack = childStack, animation = stackAnimation(slide())) { child ->
//            when(val instance = child.instance) {
//                is RootComponent.Child.ScreenHome -> RootComponent.Child.ScreenHome(instance.component)
//                else -> {}
//            }
//
//        }
        ui.Window()
    }
}
