package com.d34th.nullpointer.virtualtrainercompose.inject

import android.content.Context
import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompressModule {

    @Provides
    @Singleton
    fun provideCompressRepository(
        @ApplicationContext context: Context
    ): CompressRepoImpl = CompressRepoImpl(context)
}