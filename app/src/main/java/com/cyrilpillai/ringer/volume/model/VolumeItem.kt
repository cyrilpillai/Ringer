package com.cyrilpillai.ringer.volume.model

import androidx.annotation.DrawableRes
import com.cyrilpillai.ringer.repository.model.VolumeStreamType

data class VolumeItem(
    val type: VolumeStreamType,
    val title: String,
    @DrawableRes val iconResource: Int,
)