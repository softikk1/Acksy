package dev.softikk.acksy.data.sources

import kotlin.uuid.Uuid

interface RefreshTokenLocalSource {
    suspend fun getRefreshToken(): Uuid?
    suspend fun setRefreshToken(refreshToken: Uuid)
}