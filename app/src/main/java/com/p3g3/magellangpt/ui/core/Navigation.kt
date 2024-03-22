package com.p3g3.magellangpt.ui.core

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.composable

sealed class Destination(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Home : Destination(route = "home")
    class Menu :
        Destination(
            route = "menu"
        )
}

fun NavGraphBuilder.composable(
    destination: Destination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) = composable(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = deepLinks,
    content = content
)

fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = navigate(
    route = destination.route,
    navOptions = navOptions,
    navigatorExtras = navigatorExtras
)