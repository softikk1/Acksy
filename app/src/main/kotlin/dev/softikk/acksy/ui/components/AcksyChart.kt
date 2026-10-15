package dev.softikk.acksy.ui.components

import android.content.Intent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.common.Insets
import com.patrykandpatrick.vico.compose.common.component.LineComponent
import com.patrykandpatrick.vico.compose.common.component.ShapeComponent
import com.patrykandpatrick.vico.compose.common.component.TextComponent

@Composable
fun AcksyChart(
    modifier: Modifier = Modifier,
    modelProducer: CartesianChartModelProducer
) {
    CartesianChartHost(
        modifier = modifier,
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(
                    LineCartesianLayer.rememberLine(
                        fill = LineCartesianLayer.LineFill.single(Fill(MaterialTheme.colorScheme.primary)),
                        areaFill = LineCartesianLayer.AreaFill.single(
                            Fill(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        ),
                        pointProvider = LineCartesianLayer.PointProvider.single(
                            point = LineCartesianLayer.Point(
                                component = ShapeComponent(
                                    fill = Fill(MaterialTheme.colorScheme.surface),
                                    shape = CircleShape,
                                    strokeFill = Fill(MaterialTheme.colorScheme.primary),
                                    strokeThickness = 2.dp
                                ), size = 8.dp
                            )
                        ),
                        interpolator = LineCartesianLayer.Interpolator.catmullRom(),
                    )
                ),
                rangeProvider = CartesianLayerRangeProvider.fixed(
                    minY = 0.0, maxY = 100.0
                ),
            ),
            startAxis = VerticalAxis.rememberStart(
                line = null,
                label = TextComponent(
                    textStyle = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    padding = Insets(end = 8.dp)
                ),
                valueFormatter = CartesianValueFormatter { _, value, _ ->
                    "${value.toInt()}%"
                },
                itemPlacer = VerticalAxis.ItemPlacer.count({ 6 }),
                guideline = LineComponent(
                    fill = Fill(MaterialTheme.colorScheme.surfaceContainer)
                ),
                tick = null
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                line = LineComponent(Fill(MaterialTheme.colorScheme.onSurface)),
                label = TextComponent(
                    textStyle = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ),
                guideline = null,
                tick = LineComponent(fill = Fill(MaterialTheme.colorScheme.onSurface))
            ),
        ),
        modelProducer = modelProducer
    )
}