package com.cookandroid.everytime.core.designsystem.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

val timetableWeekDays = listOf("월", "화", "수", "목", "금")

data class TimetableGridLayout(
    val days: List<String>,
    val hours: List<String>,
    val timeColumnWidth: Dp,
    val headerHeight: Dp,
    val rowHeight: Dp,
    val cornerRadius: Dp,
    val lastRowHeight: Dp = rowHeight
)

@Composable
fun TimetableGrid(
    layout: TimetableGridLayout,
    labelColor: Color,
    labelStyle: TextStyle,
    hourAlignment: Alignment,
    modifier: Modifier = Modifier,
    hourLabelTopPadding: Dp = 0.dp,
    hourLabelEndPadding: Dp = 0.dp
) {
    val colors = EverytimeTheme.colors
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer(cacheSize = 0)

    val measuredLayout = remember(
        layout,
        labelStyle,
        density,
        textMeasurer,
        hourLabelTopPadding,
        hourLabelEndPadding
    ) {
        fun measureLabel(label: String) = textMeasurer.measure(
            text = AnnotatedString(label),
            style = labelStyle,
            maxLines = 1,
            softWrap = false
        ).size

        val hourSizes = layout.hours.map { hour -> measureLabel(hour) }
        val headerHeightPx = layout.days.maxOf { day -> measureLabel(day).height }

        with(density) {
            val hourWidthPx = hourSizes.maxOf { it.width } + hourLabelEndPadding.roundToPx()
            val hourHeightPx = hourSizes.maxOf { it.height } + hourLabelTopPadding.roundToPx()

            layout.copy(
                timeColumnWidth = maxOf(layout.timeColumnWidth, hourWidthPx.toDp()),
                headerHeight = maxOf(layout.headerHeight, headerHeightPx.toDp()),
                rowHeight = maxOf(layout.rowHeight, hourHeightPx.toDp()),
                lastRowHeight = maxOf(layout.lastRowHeight, hourHeightPx.toDp())
            )
        }
    }

    val shape = RoundedCornerShape(measuredLayout.cornerRadius)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.white)
            .border(1.dp, colors.neutral200, shape)
            .drawTimetableGridLines(measuredLayout, colors.neutral200)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(measuredLayout.headerHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(measuredLayout.timeColumnWidth))

            measuredLayout.days.forEach { day ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day,
                        color = labelColor,
                        style = labelStyle,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }

        measuredLayout.hours.forEachIndexed { index, hour ->
            val height = if (index == measuredLayout.hours.lastIndex) {
                measuredLayout.lastRowHeight
            } else {
                measuredLayout.rowHeight
            }

            Box(
                modifier = Modifier
                    .width(measuredLayout.timeColumnWidth)
                    .height(height),
                contentAlignment = hourAlignment
            ) {
                Text(
                    text = hour,
                    modifier = Modifier.padding(
                        top = hourLabelTopPadding,
                        end = hourLabelEndPadding
                    ),
                    color = labelColor,
                    style = labelStyle,
                    maxLines = 1,
                    softWrap = false
                )
            }
        }
    }
}

private fun Modifier.drawTimetableGridLines(
    layout: TimetableGridLayout,
    color: Color
): Modifier = drawBehind {
    val lineWidth = 1.dp.toPx()
    val timeColumnWidth = layout.timeColumnWidth.roundToPx().toFloat()
    val dayColumnWidth = (size.width - timeColumnWidth) / layout.days.size

    repeat(layout.days.size) { index ->
        val distanceFromStart = timeColumnWidth + index * dayColumnWidth
        val x = if (layoutDirection == LayoutDirection.Ltr) {
            distanceFromStart
        } else {
            size.width - distanceFromStart
        }

        drawLine(
            color = color,
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = lineWidth
        )
    }

    val headerHeight = layout.headerHeight.roundToPx()
    val rowHeight = layout.rowHeight.roundToPx()

    repeat(layout.hours.size) { index ->
        val y = (headerHeight + index * rowHeight).toFloat()

        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = lineWidth
        )
    }
}
