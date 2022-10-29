package com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings

import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import kotlinx.coroutines.flow.Flow

interface UserSettingsDataSource {
    val currentUser: Flow<DataUser>
    suspend fun clearData()
    suspend fun saveUser(user: DataUser)
    suspend fun changeDataUser(name: String?, pathFile: String?)
}