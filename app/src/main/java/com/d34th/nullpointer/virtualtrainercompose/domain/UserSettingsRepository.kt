package com.d34th.nullpointer.virtualtrainercompose.domain

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    val nameUser: Flow<String>
    val imgUser: Flow<String>
    val isSignInUser: Flow<Boolean>
    suspend fun changeImgUser(imgUser: Bitmap?)
    suspend fun changeNameUser(nameUser: String)
    suspend fun clearData()
}