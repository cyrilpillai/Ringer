package com.cyrilpillai.ringer.widget.view

import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

@Composable
fun HeaderWidgetView(
    text: String,
    fontSize: TextUnit = 18.sp,
    modifier: GlanceModifier = GlanceModifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = ColorProvider(
                TypedValue().apply {
                    LocalContext.current.theme.resolveAttribute(
                        android.R.attr.colorControlActivated,
                        this,
                        true
                    )
                }.resourceId
            )
        ),
        modifier = modifier
    )
}
