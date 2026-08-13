package dev.softikk.acksy.data.repository

import dev.softikk.acksy.data.sources.AuthRemoteSource
import dev.softikk.acksy.data.sources.RefreshTokenLocalSource
import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.auth.ConfirmCodeEmailModel
import dev.softikk.acksy.domain.models.auth.TokensModel
import dev.softikk.acksy.domain.repository.AuthRepository
import kotlin.uuid.Uuid

class AuthRepositoryImpl(
    private val authSource: AuthRemoteSource,
    private val refreshTokenSource: RefreshTokenLocalSource
) : AuthRepository {
    override suspend fun sendCodeEmail(email: String): Response<Unit, ErrorModel> {
        return authSource.sendCodeEmail(email)
    }

    override suspend fun confirmCodeEmail(
        email: String, code: String
    ): Response<ConfirmCodeEmailModel, ErrorModel> {
        return when (val result = authSource.confirmCodeEmail(
            email = email, code = code
        )) {
            is Response.Success -> {
                Response.Success(ConfirmCodeEmailModel(result.value.tempToken))
            }

            is Response.Failed -> {
                result
            }
        }
    }

    override suspend fun login(
        email: String, tempToken: Uuid
    ): Response<Unit, ErrorModel> {
        return when (val result = authSource.login(
            email = email, tempToken = tempToken
        )) {
            is Response.Success -> {
                val tokens = result.value
                refreshTokenSource.setRefreshToken(tokens.refresh)
                Response.Success(Unit)
            }

            is Response.Failed -> {
                result
            }
        }
    }

    override suspend fun register(
        email: String, tempToken: Uuid, username: String
    ): Response<Unit, ErrorModel> {
        return when (val result = authSource.register(
            email = email, tempToken = tempToken, username = username
        )) {
            is Response.Success -> {
                val tokens = result.value
                refreshTokenSource.setRefreshToken(tokens.refresh)
                Response.Success(Unit)
            }

            is Response.Failed -> {
                result
            }
        }
    }

    suspend fun refresh(refresh: Uuid): Response<Unit, ErrorModel> {
        return when (val result = authSource.refresh(
            refresh = refresh
        )) {
            is Response.Success -> {
                val tokens = result.value
                refreshTokenSource.setRefreshToken(tokens.refresh)
                Response.Success(Unit)
            }

            is Response.Failed -> {
                result
            }
        }
    }
}