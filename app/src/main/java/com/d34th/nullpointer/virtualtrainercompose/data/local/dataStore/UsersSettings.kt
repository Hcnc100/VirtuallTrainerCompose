package com.d34th.nullpointer.virtualtrainercompose.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersSettings(
    private val context: Context
) {
    companion object {
        private const val NAME_SETTINGS = "VirtualSettings"
        private const val KEY_IMG_USER = "KEY_IMG_USER"
        private const val KEY_NAME_USER = "KEY_NAME_USER"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_SETTINGS)
    private val keyImgUser = stringPreferencesKey(KEY_IMG_USER)
    private val keyNameUser = stringPreferencesKey(KEY_NAME_USER)

    val getDataUser: Flow<DataUser> = context.dataStore.data.map { pref ->
        DataUser(
            name = pref[keyNameUser] ?: "",
            pathFile = pref[keyImgUser] ?: ""
        )
    }

    suspend fun saveUser(newUser: DataUser) = context.dataStore.edit { pref ->
        pref[keyNameUser] = newUser.name
        pref[keyImgUser] = newUser.pathFile
    }

    suspend fun updateDataUser(name: String?, pathFile: String?) = context.dataStore.edit { pref ->
        name?.let { pref[keyNameUser] = it }
        pathFile?.let { pref[keyImgUser] = it }
    }


    suspend fun clearData() = context.dataStore.edit {
        it.clear()
    }

}