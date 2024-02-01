package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import com.d34th.nullpointer.virtualtrainercompose.core.utils.ImageUtils
import com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local.AuthLocalDataSource
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import kotlinx.coroutines.flow.Flow

class AuthRepoImpl(
    private val context: Context,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override val currentUser: Flow<AuthData?> =
        authLocalDataSource.currentUser


    override suspend fun changeDataUser(nameUser: String?, uriImg: Uri?) {
        val pathImg = uriImg?.let {
            val nameFile = "img-profile-trainer.png"
            ImageUtils.saveToInternalStorage(it.toFile(), nameFile, context)
        }
        // ! TODO fix this
//        authLocalDataSource.changeDataUser(nameUser, pathImg)

    }

    override suspend fun saveUser(name: String, uriImg: Uri) {
        val pathImg = uriImg.let {
            val nameFile = "img-profile-trainer.png"
            ImageUtils.saveToInternalStorage(it.toFile(), nameFile, context)
        }
        authLocalDataSource.saveUser(
            AuthData(
                name = name,
                pathFile = pathImg!!
            )
        )
    }


    override suspend fun clearData() =
        authLocalDataSource.clearData()

}