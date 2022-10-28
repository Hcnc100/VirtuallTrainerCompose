package com.d34th.nullpointer.virtualtrainercompose.inject

import android.content.Context
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExerciseModule {

    @Singleton
    @Provides
    fun provideExerciseRepository(
        @ApplicationContext context: Context
    ): ExerciseRepoImpl = ExerciseRepoImpl(context)
}