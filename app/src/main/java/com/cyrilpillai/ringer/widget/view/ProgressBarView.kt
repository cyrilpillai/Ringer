package com.cyrilpillai.ringer.widget.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.height
import androidx.glance.layout.width

@Composable
fun ProgressBarView(
    width: Dp,
    height: Dp,
    progress: Float,
    trackColor: Color,
    progressColor: Color
) {
    Box(
        modifier = GlanceModifier
            .width(width)
            .height(height)
            .cornerRadius(8.dp)
            .background(trackColor),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = GlanceModifier
                .width(width * progress)
                .height(height)
                .background(progressColor),
            contentAlignment = Alignment.BottomStart
        ) {}
    }
}