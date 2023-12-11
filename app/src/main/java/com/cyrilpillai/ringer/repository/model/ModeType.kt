package com.cyrilpillai.ringer.repository.model

import android.media.AudioManager

enum class ModeType(value: Int) {
    NORMAL(AudioManager.RINGER_MODE_NORMAL),
    VIBRATE(AudioManager.RINGER_MODE_VIBRATE),
    SILENT(AudioManager.RINGER_MODE_SILENT),
    UNKNOWN(-1),
}