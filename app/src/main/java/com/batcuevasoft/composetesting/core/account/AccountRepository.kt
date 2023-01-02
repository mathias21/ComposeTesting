package com.batcuevasoft.composetesting.core.account

import com.batcuevasoft.composetesting.data.account.AccountLocalDataSource
import javax.inject.Inject

interface AccountRepository {
    var name: String?
}

class AccountRepositoryImpl @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource
) : AccountRepository {
    override var name: String?
        get() = accountLocalDataSource.name
        set(value) {
            accountLocalDataSource.name = value
        }

}