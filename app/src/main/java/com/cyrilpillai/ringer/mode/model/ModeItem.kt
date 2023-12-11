package com.cyrilpillai.ringer.mode.model

import androidx.annotation.DrawableRes
import com.cyrilpillai.ringer.repository.model.ModeType

data class ModeItem(
    val type: ModeType,
    val title: String,
    @DrawableRes val iconResource: Int,
    val isEnabled: Boolean
)