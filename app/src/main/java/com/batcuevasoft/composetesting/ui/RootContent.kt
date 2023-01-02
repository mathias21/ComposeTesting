package com.batcuevasoft.composetesting.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import com.batcuevasoft.composetesting.ui.main.MainNavigation
import com.batcuevasoft.composetesting.ui.onboarding.OnboardingNavigation
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun RootContent(controller: NavHostController) {

    val onboardingNavigation = OnboardingNavigation()

    AnimatedNavHost(navController = controller, startDestination = onboardingNavigation.name) {
        onboardingNavigation.buildGraph(this, controller)
        MainNavigation().buildGraph(this, controller)
    }
}