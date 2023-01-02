package com.batcuevasoft.composetesting.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.batcuevasoft.navigation.NavigationDestination
import com.batcuevasoft.navigation.NavigationGraph
import com.google.accompanist.navigation.animation.composable

val mainStartDestination = NavigationDestination("MainStartDestination")

@OptIn(ExperimentalAnimationApi::class)
class MainNavigation(
    override val startDestination: NavigationDestination = mainStartDestination
) : NavigationGraph("MainNavigation", { controller ->

    composable(startDestination.name, exitTransition = {
        slideOutHorizontally(targetOffsetX = { -1000 })
    }) { MainStartDestination(controller) }

})

fun NavHostController.navigateToMain() {
    navigate(mainStartDestination.name)
}


@Composable
private fun MainStartDestination(controller: NavHostController) {
//    MainScreen()
}