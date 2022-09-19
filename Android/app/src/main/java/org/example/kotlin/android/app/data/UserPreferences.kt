package org.example.kotlin.android.app.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(context: Context) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(name = "my_data_store")


    val getAccessToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }


    val getUserId: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USER_ID]
        }



    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = token


        }
    }


    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val USER_ID = preferencesKey<String>("USER_ID")

    }
}


