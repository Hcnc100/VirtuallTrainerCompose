package com.d34th.nullpointer.virtualtrainercompose.domain

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserSettingsRepository {
    val nameUser: Flow<String>
    val imgUser: Flow<String>
    suspend fun changeImgUser(imgUserUri: Uri)
    suspend fun changeNameUser(nameUser: String)
    suspend fun compressImgUri(imgUri: Uri): File
    suspend fun clearData()
}