package com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings

import com.d34th.nullpointer.virtualtrainercompose.data.local.dataStore.UsersSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class UserSettingsDataSourceImpl(
    private val usersSettings: UsersSettings
) : UserSettingsDataSource {
    override val userName: Flow<String> = usersSettings.nameUser
    override val imgUser: Flow<String> = usersSettings.imgUser
    override val isSignInUser: Flow<Boolean> = userName.combine(imgUser) { name, img ->
        name.isNotEmpty() && img.isNotEmpty()
    }

    override suspend fun saveUserName(userName: String) {
        usersSettings.changeNameUser(userName)
    }

    override suspend fun saveImgUser(imgUser: String) {
        usersSettings.changeImgUser(imgUser)
    }

    override suspend fun clearData() {
        usersSettings.clearData()
    }
}