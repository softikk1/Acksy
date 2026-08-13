package dev.softikk.acksy.data.sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import dev.softikk.acksy.data.sources.RefreshTokenLocalSource
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import kotlin.uuid.Uuid

private val REFRESH_TOKEN = byteArrayPreferencesKey("refresh_token")

class RefreshTokenLocalSourceImpl(private val dataStore: DataStore<Preferences>) :
    RefreshTokenLocalSource {
    override suspend fun getRefreshToken(): Uuid? {
        val data = dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }.singleOrNull() ?: return null
        return Uuid.fromByteArray(data)
    }

    override suspend fun setRefreshToken(refreshToken: Uuid) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[REFRESH_TOKEN] = refreshToken.toByteArray()
            }
        }
    }
}