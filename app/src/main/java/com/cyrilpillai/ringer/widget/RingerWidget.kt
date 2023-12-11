package com.cyrilpillai.ringer.widget

import android.app.NotificationManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import com.cyrilpillai.ringer.R
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.widget.view.HeaderWidgetView
import com.cyrilpillai.ringer.widget.view.ModeWidgetView
import com.cyrilpillai.ringer.widget.view.VolumeWidgetView

class RingerWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        val audioManagerRepo = AudioManagerRepo.get(LocalContext.current)
        val notificationManager = LocalContext.current.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically,
            modifier = GlanceModifier
                .background(ImageProvider(R.drawable.widget_rounded_background))
                .cornerRadius(6.dp)
                .fillMaxWidth()
                .padding(16.dp)
                .appWidgetBackground()
        ) {
            HeaderWidgetView(
                text = LocalContext.current.getString(R.string.widget_media_volume_title)
            )
            VolumeWidgetView(
                audioManagerRepo = audioManagerRepo,
                modifier = GlanceModifier
                    .padding(top = 12.dp)
            )
            HeaderWidgetView(
                text = LocalContext.current.getString(R.string.widget_mode_title),
                modifier = GlanceModifier
                    .padding(top = 12.dp)
            )
            ModeWidgetView(
                audioManagerRepo = audioManagerRepo,
                notificationManager = notificationManager,
                modifier = GlanceModifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            )
        }
    }
}