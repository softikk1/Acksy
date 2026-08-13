package dev.softikk.acksy.data.sources.remote

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.data.sources.SettingsRemoteSource
import dev.softikk.acksy.data.sources.remote.resources.SettingsRes
import dev.softikk.acksy.data.sources.remote.utils.getFailedResponse
import dev.softikk.acksy.dev.softikk.acksy.entities.settings.GetSettingsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.settings.RefreshSettingsReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.enums.AccentColor
import dev.softikk.acksy.dev.softikk.acksy.enums.Languages
import dev.softikk.acksy.dev.softikk.acksy.enums.Theme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode

class SettingsRemoteSourceImpl(private val client: HttpClient) : SettingsRemoteSource {
    override suspend fun getSettings(): Response<GetSettingsRespondDto, ErrorModel> {
        val result = client.get(SettingsRes())
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<GetSettingsRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun refreshSettings(
        language: Languages, theme: Theme, accentColor: AccentColor
    ): Response<Unit, ErrorModel> {
        val result = client.post(SettingsRes.RefreshRes()) {
            setBody(
                RefreshSettingsReceiveDto(
                    language = language, theme = theme, accentColor = accentColor
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(Unit)
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }
}