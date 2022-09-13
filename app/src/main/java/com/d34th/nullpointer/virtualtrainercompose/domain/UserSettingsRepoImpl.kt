package com.d34th.nullpointer.virtualtrainercompose.domain

import android.content.Context
import android.net.Uri
import com.d34th.nullpointer.virtualtrainercompose.core.utils.ImageUtils
import com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings.UserSettingsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import java.io.File

class UserSettingsRepoImpl(
    private val context: Context,
    private val userSettingsDataSource: UserSettingsDataSource
) : UserSettingsRepository {
    override val nameUser: Flow<String> = userSettingsDataSource.imgUser
    override val imgUser: Flow<String> = userSettingsDataSource.imgUser


    override suspend fun compressImgUri(imgUri: Uri): File {
        val imageCompress = Compress.with(context, imgUri)
            .setQuality(80)
            .concrete {
                withMaxWidth(500f)
                withMaxHeight(500f)
                withScaleMode(ScaleMode.SCALE_HEIGHT)
                withIgnoreIfSmaller(true)
            }.get(Dispatchers.IO)

        return imageCompress
    }

    override suspend fun changeImgUser(imgUserUri: Uri) {
        val fileCompress = compressImgUri(imgUserUri)
        val nameFile = "img-profile-trainer.png"
        val pathImg = ImageUtils.saveToInternalStorage(fileCompress, nameFile, context)
        fileCompress.delete()

        pathImg?.let {
            userSettingsDataSource.saveImgUser(pathImg)
        }
    }

    override suspend fun changeNameUser(nameUser: String) =
        userSettingsDataSource.saveUserName(nameUser)

    override suspend fun clearData() =
        userSettingsDataSource.clearData()

}