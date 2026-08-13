package dev.softikk.acksy.data.repository

import dev.softikk.acksy.data.sources.StatisticsRemoteSource
import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.StatisticsModel
import dev.softikk.acksy.domain.repository.StatisticsRepository

class StatisticsRepositoryImpl(private val statisticsSource: StatisticsRemoteSource) :
    StatisticsRepository {
    override suspend fun getStatistics(timeZone: String): Response<StatisticsModel, ErrorModel> {
        return when (val result = statisticsSource.getStatistics(timeZone)) {
            is Response.Success -> {
                val getStatisticsDto = result.value
                Response.Success(
                    StatisticsModel(
                        score = getStatisticsDto.score,
                        total = getStatisticsDto.total,
                        bestStreak = getStatisticsDto.bestStreak,
                        lastStreak = getStatisticsDto.lastStreak,
                        confirms = getStatisticsDto.confirms
                    )
                )
            }

            is Response.Failed -> {
                result
            }
        }
    }
}