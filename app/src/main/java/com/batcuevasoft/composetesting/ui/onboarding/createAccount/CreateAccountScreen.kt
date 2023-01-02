package com.batcuevasoft.composetesting.ui.onboarding.createAccount

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.composetesting.R
import com.batcuevasoft.composetesting.ui.component.*
import com.batcuevasoft.composetesting.ui.onboarding.createAccount.CreateAccountViewModel.CreateAccountUIState
import com.batcuevasoft.composetesting.ui.theme.AppTheme

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateAccountScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    onAccountCreated: () -> Unit,
    viewModel: CreateAccountViewModel = hiltViewModel()
) {

    val state by viewModel.createAccountUIState.collectAsStateWithLifecycle()

    BackHandler(enabled = true) {
        onBackPressed()
    }

    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column {
            when (val currentState = state) {
                is CreateAccountUIState.Init -> CreateAccountContent(
                    viewModel::onNameEntered,
                    viewModel::onCreateAccountPressed
                )
                is CreateAccountUIState.Loading -> LoadingComponent()
                is CreateAccountUIState.AccountCreated -> AccountCreatedContent(onAccountCreated)
                is CreateAccountUIState.Error -> DefaultErrorContent(
                    title = stringResource(id = R.string.create_account_error_title),
                    description = stringResource(id = R.string.create_account_error_description),
                    buttonText = stringResource(id = R.string.retry_button),
                    onButtonPressed = viewModel::onCreateAccountPressed
                )
            }
        }
    }
}

@Composable
private fun AccountCreatedContent(
    onContinuePressed: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        TitleDescriptionButtonContent(
            title = stringResource(id = R.string.create_account_success_title),
            description = stringResource(id = R.string.create_account_success_description),
            buttonText = stringResource(id = R.string.continue_button),
            onButtonPressed = onContinuePressed
        )

    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
private fun CreateAccountContent(
    onNameChanged: (String) -> Unit,
    onNameDefinedPressed: () -> Unit
) {

    var input by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        TitleSubtitleHeader(
            title = stringResource(id = R.string.create_account_title),
            subtitle = stringResource(id = R.string.create_account_subtitle)
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = input,
            label = { Text(text = stringResource(id = R.string.name_label), style = MaterialTheme.typography.bodySmall) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                input = it
                onNameChanged(it)
            })

        BottomNavigationButton(buttonText = stringResource(id = R.string.continue_button)) {
            onNameDefinedPressed()
        }
    }
}

@Preview
@Composable
private fun CreateAccountContentPreview() {
    AppTheme() {
        CreateAccountContent({}, {})
    }
}