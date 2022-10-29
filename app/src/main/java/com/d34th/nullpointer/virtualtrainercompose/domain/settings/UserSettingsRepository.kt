package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.net.Uri
import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    val currentUser: Flow<DataUser>
    suspend fun clearData()
    suspend fun saveUser(name: String, uriImg: Uri)
    suspend fun changeDataUser(nameUser: String? = null, uriImg: Uri? = null)
}