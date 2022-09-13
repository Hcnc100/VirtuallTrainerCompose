package com.d34th.nullpointer.virtualtrainercompose.domain

import android.content.Context
import android.graphics.Bitmap
import com.d34th.nullpointer.virtualtrainercompose.core.utils.ImageUtils
import com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings.UserSettingsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode

class UserSettingsRepoImpl(
    private val context: Context,
    private val userSettingsDataSource: UserSettingsDataSource
) : UserSettingsRepository {
    override val nameUser: Flow<String> = userSettingsDataSource.imgUser
    override val imgUser: Flow<String> = userSettingsDataSource.imgUser
    override val isSignInUser: Flow<Boolean> = userSettingsDataSource.isSignInUser

    override suspend fun changeImgUser(imgUser: Bitmap?) {
        val fileCompress = imgUser?.let {
            Compress.with(context, it)
                .setQuality(80)
                .concrete {
                    withMaxWidth(500f)
                    withMaxHeight(500f)
                    withScaleMode(ScaleMode.SCALE_HEIGHT)
                    withIgnoreIfSmaller(true)
                }.get(Dispatchers.IO)
        }
        val nameFile = "img-profile-trainer.png"
        val pathImg = fileCompress?.let { ImageUtils.saveToInternalStorage(it, nameFile, context) }
        fileCompress?.delete()
        pathImg?.let { userSettingsDataSource.saveImgUser(it) }
    }

    override suspend fun changeNameUser(nameUser: String) =
        userSettingsDataSource.saveUserName(nameUser)

    override suspend fun clearData() =
        userSettingsDataSource.clearData()

}