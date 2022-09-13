package com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings

import kotlinx.coroutines.flow.Flow

interface UserSettingsDataSource {
    val userName: Flow<String>
    val imgUser: Flow<String>
    val isSignInUser: Flow<Boolean>
    suspend fun saveUserName(userName: String)
    suspend fun saveImgUser(imgUser: String)
    suspend fun clearData()
}