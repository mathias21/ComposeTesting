package com.batcuevasoft.composetesting.core.onboarding.createAccount

import com.batcuevasoft.composetesting.core.account.AccountRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
){

    operator fun invoke(name: String): Result<String> {
        accountRepository.name = name
        return Result.success(name)
    }
}