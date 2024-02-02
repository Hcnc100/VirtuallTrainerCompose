package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import com.d34th.nullpointer.virtualtrainercompose.core.utils.ImageUtils
import com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local.AuthLocalDataSource
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import com.d34th.nullpointer.virtualtrainercompose.models.auth.wrapper.AuthWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AuthRepoImpl(
    private val context: Context,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override val currentUser: Flow<AuthData?> =
        authLocalDataSource.currentUser


    override suspend fun clearData() =
        authLocalDataSource.clearData()

    override suspend fun saveAuthData(
        authWrapper: AuthWrapper
    ) {
        val pathImg = authWrapper.image?.let {
            val nameFile = "img-profile-trainer.png"
            ImageUtils.saveToInternalStorage(it.toFile(), nameFile, context)
        }

        val currentAuthData = (authLocalDataSource.currentUser.first() ?: AuthData()).let {
            it.copy(
                name = authWrapper.name ?: it.name,
                pathFile = pathImg ?: it.pathFile
            )
        }
        authLocalDataSource.saveUser(currentAuthData)
    }

}