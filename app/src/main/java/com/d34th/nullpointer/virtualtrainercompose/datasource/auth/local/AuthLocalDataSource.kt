package com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local

import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    val currentUser: Flow<AuthData?>
    suspend fun clearData()
    suspend fun saveUser(user: AuthData)
}