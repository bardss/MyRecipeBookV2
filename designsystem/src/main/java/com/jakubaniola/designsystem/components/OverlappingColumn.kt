package com.jakubaniola.designsystem.components

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy

@Composable
fun OverlappingColumn(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.1, to = 1.0) overlapFactor: Float = 0.5f,
    content: @Composable () -> Unit,
) {
    val measurePolicy = overlappingColumnMeasurePolicy(overlapFactor)
    Layout(
        measurePolicy = measurePolicy,
        content = content,
        modifier = modifier
    )
}

fun overlappingColumnMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val width = placeables.maxOf { it.width }
    val height = placeables.last().height +
        placeables.subList(0, placeables.size - 1).sumOf { (it.height * overlapFactor).toInt() }
    layout(width, height) {
        var yPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(0, yPos, 0f)
            yPos += (placeable.height * overlapFactor).toInt()
        }
    }
}
