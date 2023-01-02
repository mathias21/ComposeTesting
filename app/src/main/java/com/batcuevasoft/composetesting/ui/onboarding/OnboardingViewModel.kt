package com.batcuevasoft.composetesting.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batcuevasoft.composetesting.core.DispatcherProvider
import com.batcuevasoft.composetesting.core.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _accountState =
        MutableStateFlow<AccountState>(AccountState.Loading)
    val accountState: StateFlow<AccountState> get() = _accountState

    fun onInit() {
        viewModelScope.launch(dispatcherProvider.io) {
            _accountState.value = accountRepository.name?.let {
                AccountState.AccountCreated
            } ?: AccountState.NoAccountFound
        }
    }


    sealed interface AccountState {
        object Loading : AccountState
        object AccountCreated : AccountState
        object NoAccountFound : AccountState
    }
}