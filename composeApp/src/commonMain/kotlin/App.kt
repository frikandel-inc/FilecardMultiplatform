

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import kotlinx.coroutines.FlowPreview
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.DownloadScreen
import ui.HomeAppBar
import ui.HomeScreen
import ui.NfcScreen

@OptIn(ExperimentalResourceApi::class, FlowPreview::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App() {
    PreComposeApp{
        var colorScheme = lightColorScheme()
        if (isSystemInDarkTheme() == true) {
            colorScheme = darkColorScheme()
        }
        val navigator = rememberNavigator()
        MaterialTheme(colorScheme = colorScheme) {
            NavHost(
                // Assign the navigator to the NavHost
                navigator = navigator,
                // Navigation transition for the scenes in this NavHost, this is optional
                navTransition = NavTransition(),
                // The start destination
                initialRoute = "/home",
            ) {
                // Define a scene to the navigation graph
                scene(
                    // Scene's route path
                    route = "/home",
                    // Navigation transition for this scene, this is optional
                    navTransition = NavTransition(),
                ) {
                    HomeAppBar (navigator=navigator){ HomeScreen()}

                }
                scene(route= "/nfc", navTransition = NavTransition()) {
                    HomeAppBar (navigator=navigator){ NfcScreen() }

                }
                scene(route= "/downloads", navTransition = NavTransition()) {
                    HomeAppBar (navigator=navigator){ DownloadScreen() }

                }
            }


        }


    }
}
