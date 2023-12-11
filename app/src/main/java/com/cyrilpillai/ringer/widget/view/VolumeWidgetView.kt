package com.cyrilpillai.ringer.widget.view

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import com.cyrilpillai.ringer.R
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.MUSIC
import com.cyrilpillai.ringer.widget.DecreaseVolumeAction
import com.cyrilpillai.ringer.widget.IncreaseVolumeAction

@Composable
fun VolumeWidgetView(
    audioManagerRepo: AudioManagerRepo,
    modifier: GlanceModifier = GlanceModifier
) {
    val current = audioManagerRepo.getStreamVolume(MUSIC)
    val max = audioManagerRepo.getStreamMaxVolume(MUSIC)
    Row(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
    ) {
        VolumeButtonView(
            icon = R.drawable.ic_volume_down,
            action = actionRunCallback<DecreaseVolumeAction>()
        )
        Box(
            modifier = GlanceModifier
                .defaultWeight()
                .padding(horizontal = 16.dp)
        ) {
            BarView(
                width = LocalSize.current.width,
                height = 20.dp,
                progress = current.toFloat() / max,
                modifier = GlanceModifier
            )
        }
        VolumeButtonView(
            icon = R.drawable.ic_volume_up,
            action = actionRunCallback<IncreaseVolumeAction>()
        )
    }
}

@Composable
private fun VolumeButtonView(
    @DrawableRes icon: Int,
    action: Action,
    modifier: GlanceModifier = GlanceModifier
) {
    Column(
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(30.dp)
            .background(ImageProvider(R.drawable.circular_background_selected))
            .padding(8.dp)
            .clickable(action)
    ) {
        Image(
            provider = ImageProvider(
                resId = icon,
            ),
            contentDescription = "volume_button",
            modifier = GlanceModifier
                .clickable(onClick = action)
        )
    }
}

@Composable
private fun BarView(
    width: Dp,
    height: Dp,
    progress: Float,
    modifier: GlanceModifier = GlanceModifier
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .background(ImageProvider(R.drawable.progress_bar_rounded_corners_unfilled))
    ) {
        Box(
            modifier = GlanceModifier
                .width(width * progress)
                .height(height)
                .background(ImageProvider(R.drawable.progress_bar_rounded_corners_filled))
        ) {
        }
    }
}