package com.cyrilpillai.ringer.volume.repository

import android.content.Context
import com.cyrilpillai.ringer.R
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.ALARM
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.MUSIC
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.SYSTEM
import com.cyrilpillai.ringer.volume.model.VolumeItem

fun getVolumeItems(context: Context): List<VolumeItem> {
    return listOf(
        VolumeItem(
            type = MUSIC,
            title = context.getString(R.string.volume_music_title),
            iconResource = R.drawable.ic_music_volume
        ),
        VolumeItem(
            type = ALARM,
            title = context.getString(R.string.volume_alarm_title),
            iconResource = R.drawable.ic_alarm_volume
        ),
        VolumeItem(
            type = SYSTEM,
            title = context.getString(R.string.volume_ring_title),
            iconResource = R.drawable.ic_system_volume
        )
    )
}