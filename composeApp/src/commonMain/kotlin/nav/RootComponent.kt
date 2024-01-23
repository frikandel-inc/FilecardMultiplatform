package nav

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable



interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    // It's possible to pop multiple screens at a time on iOS
    fun onBackClicked(toIndex: Int)

    // Defines all possible child components
    sealed class Child {
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.List, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.List -> RootComponent.Child.ListChild(listComponent(componentContext))
            is Config.Details -> RootComponent.Child.DetailsChild(
                detailsComponent(
                    componentContext,
                    config
                )
            )
        }

    private fun listComponent(componentContext: ComponentContext): ListComponent =
        DefaultListComponent(
            componentContext = componentContext,
            onItemSelected = { item: String -> // Supply dependencies and callbacks
                navigation.push(Config.Details(item = item)) // Push the details component
            },
        )

    private fun detailsComponent(componentContext: ComponentContext, config: Config.Details): DetailsComponent =
        DefaultDetailsComponent(
            componentContext = componentContext,
            onItemSelected = {

            }
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Details(val item: String) : Config
    }
}


//class RootComponent(
//    componentContext: ComponentContext
//): ComponentContext by componentContext {
//
//    private val navigation = StackNavigation<Configuration>()
//    val childStack: Value<ChildStack<*, Child>> = childStack(
//        source = navigation,
//        serializer = Configuration.serializer(),
//        initialConfiguration = nav.RootComponent.Configuration.ScreenHome,
//        handleBackButton = true,
//        childFactory = ::createChild
//    )
//
//    private fun createChild(
//        config: Configuration,
//        context: ComponentContext
//    ): Child {
//        return when(config) {
//            Configuration.ScreenHome -> Child.ScreenHome(
//                ScreenAComponent(
//                    componentContext = context,
//                    onNavigateToScreenB = { text ->
//                        navigation.pushNew(Configuration.ScreenB(text))
//                    }
//                )
//            )
//            is Configuration.ScreenB -> Child.ScreenB(
//                ScreenBComponent(
//                    text = config.text,
//                    componentContext = context,
//                    onGoBack = {
//                        navigation.pop()
//                    }
//                )
//            )
//        }
//    }
//
//    sealed class Child {
//        data class ScreenHome(val component: ScreenAComponent): Child()
//        data class ScreenB(val component: ScreenBComponent): Child()
//    }
//
//    @Serializable
//    sealed class Configuration {
//        @Serializable
//        data object ScreenHome: Configuration()
//
//        @Serializable
//        data class ScreenB(val text: String): Configuration()
//    }
//}