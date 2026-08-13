package dev.softikk.acksy.data.sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.softikk.acksy.data.sources.TokensLocalSource
import dev.softikk.acksy.domain.models.auth.TokensModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.serialization.json.Json
import kotlin.uuid.Uuid

private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

class TokensLocalSourceImpl(private val dataStore: DataStore<Preferences>) :
    TokensLocalSource {
    override suspend fun getRefreshToken(): TokensModel? {
        val data = dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }.singleOrNull()
        return data?.let { Json.decodeFromString(data) }
    }

    override suspend fun setRefreshToken(refreshToken: Uuid, accessToken: String) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[REFRESH_TOKEN] = Json.encodeToString(
                    TokensModel(
                        access = accessToken, refresh = refreshToken
                    )
                )
            }
        }
    }
}