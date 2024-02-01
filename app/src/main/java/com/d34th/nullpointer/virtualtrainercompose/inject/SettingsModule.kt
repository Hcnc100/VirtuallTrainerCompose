package com.d34th.nullpointer.virtualtrainercompose.inject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.d34th.nullpointer.virtualtrainercompose.data.auth.local.AuthDataStore
import com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local.AuthLocalDataSource
import com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local.AuthLocalDataSourceImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.AuthRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideUserSettings(
        dataStore: DataStore<Preferences>
    ): AuthDataStore = AuthDataStore(dataStore)


    @Provides
    @Singleton
    fun provideUserSettingsDataSource(
        authDataStore: AuthDataStore
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(authDataStore)

    @Provides
    @Singleton
    fun provideUserSettingsRepository(
        @ApplicationContext context: Context,
        authLocalDataSource: AuthLocalDataSource
    ): AuthRepoImpl = AuthRepoImpl(context, authLocalDataSource)
}