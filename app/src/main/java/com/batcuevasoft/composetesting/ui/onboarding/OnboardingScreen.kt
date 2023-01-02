package com.batcuevasoft.composetesting.ui.onboarding

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.composetesting.ui.component.BottomNavigationButton
import com.batcuevasoft.composetesting.ui.component.LoadingComponent
import com.batcuevasoft.composetesting.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnboardingScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    onContinueButtonPressed: () -> Unit,
    onExistingAccount: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    BackHandler(enabled = true) {
        onBackPressed()
    }

    LaunchedEffect(viewModel) {
        viewModel.onInit()
    }

    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        val rootState by viewModel.accountState.collectAsStateWithLifecycle()
        when (rootState) {
            OnboardingViewModel.AccountState.Loading -> LoadingComponent()
            OnboardingViewModel.AccountState.AccountCreated -> onExistingAccount()
            OnboardingViewModel.AccountState.NoAccountFound -> NoAccountContent(
                onContinueButtonPressed
            )
        }
    }
}

@Composable
private fun NoAccountContent(
    onContinueButtonPressed: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(id = R.string.welcome_to_title) + " " + stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        BottomNavigationButton(buttonText = stringResource(id = R.string.continue_button)) {
            onContinueButtonPressed()
        }
    }
}