package com.cyrilpillai.ringer.widget.view

import android.app.NotificationManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.padding
import androidx.glance.layout.size
import com.cyrilpillai.ringer.R
import com.cyrilpillai.ringer.mode.model.ModeItem
import com.cyrilpillai.ringer.mode.repository.getModeItems
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.repository.model.ModeType
import com.cyrilpillai.ringer.repository.model.ModeType.NORMAL
import com.cyrilpillai.ringer.repository.model.ModeType.SILENT
import com.cyrilpillai.ringer.repository.model.ModeType.UNKNOWN
import com.cyrilpillai.ringer.repository.model.ModeType.VIBRATE
import com.cyrilpillai.ringer.widget.NormalModeAction
import com.cyrilpillai.ringer.widget.SilentModeAction
import com.cyrilpillai.ringer.widget.VibrateModeAction

@Composable
fun ModeWidgetView(
    audioManagerRepo: AudioManagerRepo,
    notificationManager: NotificationManager,
    modifier: GlanceModifier = GlanceModifier
) {
    Row(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        getModeItems(
            LocalContext.current,
            notificationManager.isNotificationPolicyAccessGranted
        ).forEach {
            ModeItemView(
                modeItem = it,
                selectedModeType = audioManagerRepo.getRingerMode(),
                modifier = GlanceModifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun ModeItemView(
    modeItem: ModeItem,
    selectedModeType: ModeType,
    modifier: GlanceModifier = GlanceModifier
) {
    val action = when (modeItem.type) {
        NORMAL -> actionRunCallback<NormalModeAction>()
        VIBRATE -> actionRunCallback<VibrateModeAction>()
        SILENT -> actionRunCallback<SilentModeAction>()
        UNKNOWN -> actionRunCallback<NormalModeAction>()
    }
    val background = if (modeItem.type == selectedModeType) {
        R.drawable.circular_background_selected
    } else {
        R.drawable.circular_background_unselected
    }
    Box(modifier = modifier) {
        Column(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier
                .size(60.dp)
                .background(ImageProvider(background))
                .padding(8.dp)
                .clickable(action)
        ) {
            Image(
                provider = ImageProvider(
                    resId = modeItem.iconResource
                ),
                contentDescription = modeItem.title,
                modifier = modifier
                    .clickable(action)
            )
        }
    }
}