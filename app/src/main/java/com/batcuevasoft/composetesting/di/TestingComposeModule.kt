package com.batcuevasoft.composetesting.di

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.batcuevasoft.composetesting.core.DispatcherProvider
import com.batcuevasoft.composetesting.core.DispatcherProviderImp
import com.batcuevasoft.composetesting.core.account.AccountRepository
import com.batcuevasoft.composetesting.core.account.AccountRepositoryImpl
import com.batcuevasoft.composetesting.data.account.AccountLocalDataSource
import com.batcuevasoft.composetesting.data.account.AccountLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object ComposeTestingModule {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindsModule {
        @Binds
        fun bindAccountRepository(implementation: AccountRepositoryImpl): AccountRepository

        @Binds
        fun bindAccountLocalDataSource(implementation: AccountLocalDataSourceImpl): AccountLocalDataSource
    }

    @Provides
    @Singleton
    internal fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context, ENCRYPTED_SHARED_PREFS_KEY_ALIAS)
            .setKeyGenParameterSpec(getKeySpecsWithAlias(ENCRYPTED_SHARED_PREFS_KEY_ALIAS, false))
            .build()

        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImp()
    }

    private fun getKeySpecsWithAlias(alias: String, isAuthNeeded: Boolean): KeyGenParameterSpec {

        val authenticationValidityDurationSeconds = 15

        with(
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
        ) {
            setKeySize(256)
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            if (isAuthNeeded) {
                setUserAuthenticationRequired(true)
                setUserAuthenticationParameters(
                    authenticationValidityDurationSeconds,
                    KeyProperties.AUTH_DEVICE_CREDENTIAL
                            or KeyProperties.AUTH_BIOMETRIC_STRONG
                )
            }
            return build()
        }
    }

    private const val ENCRYPTED_SHARED_PREFS_KEY_ALIAS = "alias"
    private const val ENCRYPTED_SHARED_PREFS_FILE = "file"
}
