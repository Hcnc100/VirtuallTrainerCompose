package com.d34th.nullpointer.virtualtrainercompose.domain.settings

import android.net.Uri
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import com.d34th.nullpointer.virtualtrainercompose.models.auth.wrapper.AuthWrapper
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<AuthData?>
    suspend fun clearData()
    suspend fun saveAuthData(authWrapper: AuthWrapper)
}