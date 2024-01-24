

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        var userid: Long? by remember { mutableStateOf(null) }

        val navigator = rememberNavigator()
        MaterialTheme(colorScheme = colorScheme) {
            NavHost(
                // Assign the navigator to the NavHost
                navigator = navigator,
                // Navigation transition for the scenes in this NavHost, this is optional
                navTransition = NavTransition(
                    createTransition = fadeIn(animationSpec = TweenSpec(100),initialAlpha = 0.9F),
                    destroyTransition = fadeOut(animationSpec = TweenSpec(100),targetAlpha = 0.9F),
                    pauseTransition = fadeOut(animationSpec = TweenSpec(100),targetAlpha = 0.9F),
                    resumeTransition = fadeIn(animationSpec = TweenSpec(100),initialAlpha = 0.9F),
                    enterTargetContentZIndex = 1f,


                    ),
                // The start destination
                initialRoute = "/home",
            ) {

                // Define a scene to the navigation graph
                scene(route = "/home",) {
                    HomeAppBar (navigator=navigator){ HomeScreen()}

                }
                scene(route= "/nfc") {
                    HomeAppBar (navigator=navigator){  userid = NfcScreen(userid) }

                }
                scene(route= "/file") {
                    HomeAppBar (navigator=navigator){ DownloadScreen(userid) }

                }
            }


        }


    }
}
