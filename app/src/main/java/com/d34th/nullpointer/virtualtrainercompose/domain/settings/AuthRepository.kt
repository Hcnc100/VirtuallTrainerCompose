package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.net.Uri
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<AuthData?>
    suspend fun clearData()
    suspend fun saveUser(name: String, uriImg: Uri)
    suspend fun changeDataUser(nameUser: String? = null, uriImg: Uri? = null)
}