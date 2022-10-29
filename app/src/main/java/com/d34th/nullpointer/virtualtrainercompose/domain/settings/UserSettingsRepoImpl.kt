package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import com.d34th.nullpointer.virtualtrainercompose.core.utils.ImageUtils
import com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings.UserSettingsDataSource
import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import kotlinx.coroutines.flow.Flow

class UserSettingsRepoImpl(
    private val context: Context,
    private val userSettingsDataSource: UserSettingsDataSource
) : UserSettingsRepository {

    override val currentUser: Flow<DataUser> =
        userSettingsDataSource.currentUser


    override suspend fun changeDataUser(nameUser: String?, uriImg: Uri?) {
        val pathImg = uriImg?.let {
            val nameFile = "img-profile-trainer.png"
            ImageUtils.saveToInternalStorage(it.toFile(), nameFile, context)
        }
        userSettingsDataSource.changeDataUser(nameUser, pathImg)

    }

    override suspend fun saveUser(name: String, uriImg: Uri) {
        val pathImg = uriImg.let {
            val nameFile = "img-profile-trainer.png"
            ImageUtils.saveToInternalStorage(it.toFile(), nameFile, context)
        }
        userSettingsDataSource.saveUser(
            DataUser(
                name = name,
                pathFile = pathImg!!
            )
        )
    }


    override suspend fun clearData() =
        userSettingsDataSource.clearData()

}