package com.d34th.nullpointer.virtualtrainercompose.inject

import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepository
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepository
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.UserSettingsRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.UserSettingsRepository
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

    @Binds
    @Singleton
    abstract fun provideExerciseRepository(
        exerciseRepoImpl: ExerciseRepoImpl
    ): ExerciseRepository

    @Binds
    @Singleton
    abstract fun provideCompressRepository(
        compressRepoImpl: CompressRepoImpl
    ): CompressRepository
}