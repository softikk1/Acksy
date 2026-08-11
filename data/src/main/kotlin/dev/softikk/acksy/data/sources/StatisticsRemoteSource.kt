package dev.softikk.acksy.data.sources

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.dev.softikk.acksy.entities.statistics.GetStatisticsRespondDto

interface StatisticsRemoteSource {
    suspend fun getStatistics(timeZone: String): Response<GetStatisticsRespondDto, ErrorModel>
}