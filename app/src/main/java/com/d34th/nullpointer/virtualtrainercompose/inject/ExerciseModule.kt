package com.d34th.nullpointer.virtualtrainercompose.inject

import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExerciseModule {

    @Singleton
    @Provides
    fun provideExerciseRepository(): ExerciseRepoImpl = ExerciseRepoImpl()
}