package com.d34th.nullpointer.virtualtrainercompose.data.local.userSettings

import com.d34th.nullpointer.virtualtrainercompose.data.local.dataStore.UsersSettings
import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import kotlinx.coroutines.flow.Flow

class UserSettingsDataSourceImpl(
    private val usersSettings: UsersSettings
) : UserSettingsDataSource {

    override val currentUser: Flow<DataUser> = usersSettings.getDataUser

    override suspend fun saveUser(user: DataUser) {
        usersSettings.saveUser(user)
    }

    override suspend fun changeDataUser(name: String?, pathFile: String?) {
        usersSettings.updateDataUser(name, pathFile)
    }


    override suspend fun clearData() {
        usersSettings.clearData()
    }
}