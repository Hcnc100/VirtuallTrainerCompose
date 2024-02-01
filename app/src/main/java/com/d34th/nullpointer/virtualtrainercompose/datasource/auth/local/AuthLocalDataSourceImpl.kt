package com.d34th.nullpointer.virtualtrainercompose.datasource.auth.local

import com.d34th.nullpointer.virtualtrainercompose.data.auth.local.AuthDataStore
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl(
    private val authDataStore: AuthDataStore
) : AuthLocalDataSource {

    override val currentUser: Flow<AuthData?> = authDataStore.getAuthData

    override suspend fun saveUser(user: AuthData) {
        authDataStore.saveUser(user)
    }


    override suspend fun clearData() {
        authDataStore.clearData()
    }
}