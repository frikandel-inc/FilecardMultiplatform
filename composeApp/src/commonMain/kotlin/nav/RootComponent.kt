package nav

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = nav.RootComponent.Configuration.ScreenHome,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when(config) {
            Configuration.ScreenHome -> Child.ScreenHome(
                ScreenAComponent(
                    componentContext = context,
                    onNavigateToScreenB = { text ->
                        navigation.pushNew(Configuration.ScreenB(text))
                    }
                )
            )
            is Configuration.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    text = config.text,
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    sealed class Child {
        data class ScreenHome(val component: ScreenAComponent): Child()
        data class ScreenB(val component: ScreenBComponent): Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object ScreenHome: Configuration()

        @Serializable
        data class ScreenB(val text: String): Configuration()
    }
}