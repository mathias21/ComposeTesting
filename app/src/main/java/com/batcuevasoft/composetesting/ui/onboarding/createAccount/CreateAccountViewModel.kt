package com.batcuevasoft.composetesting.ui.onboarding.createAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batcuevasoft.composetesting.core.DispatcherProvider
import com.batcuevasoft.composetesting.core.onboarding.createAccount.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    private val _createAccountUIState =
        MutableStateFlow<CreateAccountUIState>(CreateAccountUIState.Init)
    val createAccountUIState: StateFlow<CreateAccountUIState> get() = _createAccountUIState

    private var currentName = ""

    fun onNameEntered(name: String) {
        currentName = name
    }

    fun onCreateAccountPressed() {
        viewModelScope.launch(dispatcherProvider.io) {
            createAccountUseCase(currentName).fold(
                onSuccess = { _createAccountUIState.value = CreateAccountUIState.AccountCreated },
                onFailure = {_createAccountUIState.value = CreateAccountUIState.Error }
            )
        }
    }


    sealed interface CreateAccountUIState {
        object Init : CreateAccountUIState
        object Loading : CreateAccountUIState
        object AccountCreated : CreateAccountUIState
        object Error : CreateAccountUIState
    }
}