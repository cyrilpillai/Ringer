package com.cyrilpillai.ringer.volume.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cyrilpillai.ringer.common.view.HeaderView
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.volume.model.VolumeItem

@Composable
fun VolumeItemView(
    volumeItem: VolumeItem,
    audioManagerRepo: AudioManagerRepo,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentHeight()
    ) {

        val initialVolume = audioManagerRepo.getStreamVolume(volumeItem.type)
        val minVolume = audioManagerRepo.getStreamMinVolume(volumeItem.type)
        val maxVolume = audioManagerRepo.getStreamMaxVolume(volumeItem.type)

        var sliderPosition by remember {
            mutableStateOf(initialVolume.toFloat())
        }
        HeaderView(
            title = volumeItem.title,
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(
                    id = volumeItem.iconResource
                ),
                contentDescription = "icon",
                tint = MaterialTheme.colorScheme.primary
            )
            Slider(
                value = sliderPosition,
                onValueChange = {
                    if (it >= minVolume.toFloat()) {
                        sliderPosition = it
                        audioManagerRepo.setStreamVolume(
                            volumeItem.type,
                            sliderPosition.toInt()
                        )
                    }
                },
                valueRange = 0f..maxVolume.toFloat(),
                steps = maxVolume,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}