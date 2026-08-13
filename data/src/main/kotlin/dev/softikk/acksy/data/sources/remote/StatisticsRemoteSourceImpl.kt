package dev.softikk.acksy.data.sources.remote

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.data.sources.StatisticsRemoteSource
import dev.softikk.acksy.data.sources.remote.resources.StatisticsRes
import dev.softikk.acksy.data.sources.remote.utils.getFailedResponse
import dev.softikk.acksy.dev.softikk.acksy.entities.statistics.GetStatisticsReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.statistics.GetStatisticsRespondDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode

class StatisticsRemoteSourceImpl(private val client: HttpClient) : StatisticsRemoteSource {
    override suspend fun getStatistics(timeZone: String): Response<GetStatisticsRespondDto, ErrorModel> {
        val result = client.get(StatisticsRes()) {
            setBody(GetStatisticsReceiveDto(timeZone = timeZone))
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<GetStatisticsRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }
}