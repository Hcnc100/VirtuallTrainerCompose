package com.d34th.nullpointer.virtualtrainercompose.inject

import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepository
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepository
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.AuthRepoImpl
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.AuthRepository
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
        authRepoImpl: AuthRepoImpl
    ): AuthRepository

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