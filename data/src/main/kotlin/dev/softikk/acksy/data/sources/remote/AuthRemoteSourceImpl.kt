package dev.softikk.acksy.data.sources.remote

import dev.softikk.acksy.data.models.ErrorModel
import dev.softikk.acksy.data.models.Response
import dev.softikk.acksy.data.sources.AuthRemoteSource
import dev.softikk.acksy.data.sources.remote.resources.AuthRes
import dev.softikk.acksy.data.sources.remote.utils.getFailedResponse
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.ConfirmCodeEmailReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.ConfirmCodeEmailRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.LoginReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.LoginRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RefreshReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RefreshRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RegisterReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RegisterRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.SendCodeEmailReceiveDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlin.uuid.Uuid

class AuthRemoteSourceImpl(private val client: HttpClient) : AuthRemoteSource {
    override suspend fun sendCodeEmail(email: String): Response<Unit, ErrorModel> {
        val result = client.post(AuthRes.SendCodeEmailRes()) {
            setBody(
                SendCodeEmailReceiveDto(
                    email = email
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> Response.Success(Unit)
            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun confirmCodeEmail(
        email: String, code: String
    ): Response<ConfirmCodeEmailRespondDto, ErrorModel> {
        val result = client.post(AuthRes.ConfirmCodeEmailRes()) {
            setBody(
                ConfirmCodeEmailReceiveDto(
                    email = email, code = code
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<ConfirmCodeEmailRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun login(
        email: String, tempToken: Uuid
    ): Response<LoginRespondDto, ErrorModel> {
        val result = client.post(AuthRes.LoginRes()) {
            setBody(
                LoginReceiveDto(
                    email = email, tempToken = tempToken
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<LoginRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun register(
        email: String, tempToken: Uuid, username: String
    ): Response<RegisterRespondDto, ErrorModel> {
        val result = client.post(AuthRes.RegisterRes()) {
            setBody(
                RegisterReceiveDto(
                    email = email, tempToken = tempToken, username = ""
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<RegisterRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun refresh(refresh: Uuid): Response<RefreshRespondDto, ErrorModel> {
        val result = client.post(AuthRes.RefreshRes()) {
            setBody(
                RefreshReceiveDto(
                    refresh = refresh
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<RefreshRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }
}