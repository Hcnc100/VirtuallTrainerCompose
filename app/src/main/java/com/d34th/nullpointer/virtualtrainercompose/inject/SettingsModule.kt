package com.d34th.nullpointer.virtualtrainercompose.inject

import android.content.Context
import com.d34th.nullpointer.virtualtrainercompose.data.local.dataStore.UsersSettings
import com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings.UserSettingsDataSource
import com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings.UserSettingsDataSourceImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.UserSettingsRepoImpl
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
        @ApplicationContext context: Context
    ): UsersSettings = UsersSettings(context)


    @Provides
    @Singleton
    fun provideUserSettingsDataSource(
        usersSettings: UsersSettings
    ): UserSettingsDataSource = UserSettingsDataSourceImpl(usersSettings)

    @Provides
    @Singleton
    fun provideUserSettingsRepository(
        @ApplicationContext context: Context,
        userSettingsDataSource: UserSettingsDataSource
    ): UserSettingsRepoImpl = UserSettingsRepoImpl(context, userSettingsDataSource)
}