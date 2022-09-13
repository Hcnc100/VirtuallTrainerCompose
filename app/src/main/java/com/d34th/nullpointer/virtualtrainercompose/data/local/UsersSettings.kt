package com.d34th.nullpointer.virtualtrainercompose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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

    val nameUser: Flow<String?> = context.dataStore.data.map { pref ->
        pref[keyNameUser]
    }

    val imgUser: Flow<String?> = context.dataStore.data.map { pref ->
        pref[keyImgUser]
    }

    suspend fun changeNameUser(newName: String) = context.dataStore.edit { pref ->
        pref[keyNameUser] = newName
    }

    suspend fun changeImgUser(newName: String) = context.dataStore.edit { pref ->
        pref[keyImgUser] = newName
    }

    suspend fun clearData() = context.dataStore.edit {
        it.clear()
    }

}