package com.batcuevasoft.composetesting.ui.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.batcuevasoft.navigation.NavigationDestination
import com.batcuevasoft.navigation.NavigationGraph
import com.batcuevasoft.navigation.navigate
import com.batcuevasoft.composetesting.ui.main.navigateToMain
import com.batcuevasoft.composetesting.ui.onboarding.createAccount.CreateAccountScreen
import com.google.accompanist.navigation.animation.composable

val createAccount = NavigationDestination("OnboardingCreateAccount")

@OptIn(ExperimentalAnimationApi::class)
class OnboardingNavigation(
    override val startDestination: NavigationDestination = NavigationDestination("OnboardingStartDestination")
) : NavigationGraph("OnboardingNavigation", { controller ->

    composable(startDestination.name, exitTransition = {
        slideOutHorizontally(targetOffsetX = { -1000 })
    }) { OnboardingInitialDestination(controller) }

    composable(createAccount.name, exitTransition = {
        slideOutHorizontally(targetOffsetX = { -1000 })
    }) { OnboardingCreateAccountNavigation(controller) }

})


private fun NavHostController.navigateToWalletCreate() {
    navigate(createAccount)
}

@Composable
private fun OnboardingInitialDestination(controller: NavHostController) {
    OnboardingScreen(
        onBackPressed = { controller.popBackStack() },
        onContinueButtonPressed = { controller.navigateToWalletCreate() },
        onExistingAccount = { controller.navigateToMain() }
    )
}

@Composable
private fun OnboardingCreateAccountNavigation(controller: NavHostController) {
    CreateAccountScreen(
        onBackPressed = { controller.popBackStack() },
        onAccountCreated = { controller.navigateToMain() }
    )
}