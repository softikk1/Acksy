package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineModel
import dev.softikk.acksy.R
import dev.softikk.acksy.domain.models.StatisticsModel
import dev.softikk.acksy.ui.components.AcksyChart
import dev.softikk.acksy.ui.components.AcksyCounterStatistics
import dev.softikk.acksy.ui.components.AcksyHeatMapCalendar
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksyStatisticsFolder
import dev.softikk.acksy.ui.components.Statistics
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.AcksyTheme
import dev.softikk.acksy.ui.theme.Dimens
import kotlinx.datetime.LocalDateTime

val TestStatisticsModel = StatisticsModel(
    score = 81u, total = 323u, bestStreak = 102u, lastStreak = 20u, confirms = setOf(
        LocalDateTime(2026, 7, 3, 10, 15),
        LocalDateTime(2026, 7, 7, 14, 30),
        LocalDateTime(2026, 7, 12, 9, 0),
        LocalDateTime(2026, 7, 18, 18, 45),
        LocalDateTime(2026, 7, 25, 12, 0),

        LocalDateTime(2026, 8, 2, 8, 30),
        LocalDateTime(2026, 8, 5, 11, 0),
        LocalDateTime(2026, 8, 10, 16, 20),
        LocalDateTime(2026, 8, 14, 20, 0),
        LocalDateTime(2026, 8, 21, 13, 15),
        LocalDateTime(2026, 8, 28, 19, 45),

        LocalDateTime(2026, 10, 1, 9, 0),
        LocalDateTime(2026, 10, 6, 15, 30),
        LocalDateTime(2026, 10, 12, 11, 10),
        LocalDateTime(2026, 10, 19, 17, 0),
        LocalDateTime(2026, 10, 25, 10, 0)
    )
)

@Composable
fun StatisticsScreen(backStack: NavBackStack<NavKey>) {
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineModel {
                series(
                    TestStatisticsModel.confirms.sorted().map { it.day })
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Paddings.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
    ) {
        AcksyScreenName(
            text = stringResource(R.string.statistics_screen_name)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.xsPadding)
        ) {
            AcksyCounterStatistics(
                count = TestStatisticsModel.score.toInt(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall,
                suffix = "%"
            )
            Text(
                text = stringResource(R.string.statistics_screen_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        AcksyStatisticsFolder(
            modifier = Modifier.fillMaxWidth(), items = listOf(
                Statistics(
                    count = TestStatisticsModel.total.toInt(),
                    description = stringResource(R.string.statistics_screen_statistic_folder_total)
                ), Statistics(
                    count = TestStatisticsModel.bestStreak.toInt(),
                    description = stringResource(R.string.statistics_screen_statistic_folder_best_streak)
                ), Statistics(
                    count = TestStatisticsModel.lastStreak.toInt(),
                    description = stringResource(R.string.statistics_screen_statistic_folder_streak)
                )
            )
        )
        Text(
            text = stringResource(R.string.statistics_screen_subtitle_chart),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        AcksyChart(
            modelProducer = modelProducer
        )
        Text(
            text = stringResource(R.string.statistics_screen_subtitle_heatmap),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        AcksyHeatMapCalendar(
            isStatistic = backStack.lastOrNull() == Routes.Statistics,
            confirmsHabit = TestStatisticsModel.confirms.map { it.date })
    }
}