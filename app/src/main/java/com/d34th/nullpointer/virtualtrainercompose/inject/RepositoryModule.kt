package com.d34th.nullpointer.virtualtrainercompose.inject

import com.d34th.nullpointer.virtualtrainercompose.domain.UserSettingsRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.UserSettingsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    abstract fun provideSettingRepository(
        userSettingsRepoImpl: UserSettingsRepoImpl
    ): UserSettingsRepository
}