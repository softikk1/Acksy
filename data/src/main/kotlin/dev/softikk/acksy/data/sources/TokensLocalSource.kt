package dev.softikk.acksy.data.sources

import dev.softikk.acksy.domain.models.auth.TokensModel
import kotlin.uuid.Uuid

interface TokensLocalSource {
    suspend fun getRefreshToken(): TokensModel?
    suspend fun setRefreshToken(refreshToken: Uuid, accessToken: String)
}