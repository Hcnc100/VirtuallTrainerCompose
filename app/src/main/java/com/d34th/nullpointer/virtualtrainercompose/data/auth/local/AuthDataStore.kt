package com.d34th.nullpointer.virtualtrainercompose.data.auth.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthDataStore(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private const val AUTH_DATA_KEY = "AUTH_DATA_KEY"
    }


    private val authDataKey = stringPreferencesKey(AUTH_DATA_KEY)

    val getAuthData: Flow<AuthData?> = dataStore.data.map { pref ->
        pref[authDataKey]?.let {
            Json.decodeFromString(it)
        }
    }

    suspend fun saveUser(newUser: AuthData) = dataStore.edit { pref ->
        pref[authDataKey] = Json.encodeToString(newUser)
    }


    suspend fun clearData() = dataStore.edit {pref->
        pref.remove(authDataKey)
    }

}