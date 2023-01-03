package com.batcuevasoft.composetesting.onboarding

import android.content.SharedPreferences
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.batcuevasoft.composetesting.core.account.AccountRepository
import com.batcuevasoft.composetesting.di.ComposeTestingModule
import com.batcuevasoft.composetesting.ui.MainActivity
import com.batcuevasoft.composetesting.ui.onboarding.OnboardingScreen
import com.batcuevasoft.composetesting.ui.theme.AppTheme
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@UninstallModules(ComposeTestingModule.BindsModule::class)
@HiltAndroidTest
class OnboardingTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    @JvmField
    val accountRepository: AccountRepository = mock()

    @BindValue
    @JvmField
    val sharedPreferences: SharedPreferences = mock()


    @Test
    fun onboardingIsDisplayedOnFirstRunTest() {
        var isContinuePressed = false
        whenever(accountRepository.name).thenReturn(null)

        composeTestRule.activity.setContent {
            AppTheme {
                OnboardingScreen(
                    {},
                    onContinueButtonPressed = {
                        isContinuePressed = true
                    },
                    onExistingAccount = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Welcome to ComposeTesting").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continue").performClick()
        assert(isContinuePressed)
    }
}