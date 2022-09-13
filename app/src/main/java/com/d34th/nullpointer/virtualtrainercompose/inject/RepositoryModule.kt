package com.d34th.nullpointer.virtualtrainercompose.inject

import com.d34th.nullpointer.virtualtrainercompose.domain.UserSettingsRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.UserSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSettingRepository(
        userSettingsRepoImpl: UserSettingsRepoImpl
    ): UserSettingsRepository
}