package dev.softikk.acksy.domain.repository

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.StatisticsModel

interface StatisticsRepository {
    suspend fun getStatistics(timeZone: String): Response<StatisticsModel, ErrorModel>
}