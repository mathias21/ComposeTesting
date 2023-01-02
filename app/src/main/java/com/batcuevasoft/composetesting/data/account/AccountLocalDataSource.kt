package com.batcuevasoft.composetesting.data.account

import android.content.SharedPreferences
import javax.inject.Inject

interface AccountLocalDataSource {
    var name: String?
}

internal class AccountLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AccountLocalDataSource {

    override var name: String?
        get() = sharedPreferences.getString(NAME, null)
        set(value) = sharedPreferences.edit().putString(NAME, value).apply()

    companion object {
        private const val NAME = "name"
    }
}