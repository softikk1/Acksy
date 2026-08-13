package dev.softikk.acksy.domain.repository

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.auth.ConfirmCodeEmailModel
import dev.softikk.acksy.domain.models.auth.TokensModel
import kotlin.uuid.Uuid

interface AuthRepository {
    suspend fun sendCodeEmail(email: String): Response<Unit, ErrorModel>
    suspend fun confirmCodeEmail(
        email: String, code: String
    ): Response<ConfirmCodeEmailModel, ErrorModel>

    suspend fun login(
        email: String, tempToken: Uuid
    ): Response<TokensModel, ErrorModel>

    suspend fun register(
        email: String, tempToken: Uuid, username: String
    ): Response<TokensModel, ErrorModel>

    suspend fun refresh(refresh: Uuid): Response<TokensModel, ErrorModel>
}