package com.cyrilpillai.ringer.repository.model

import android.media.AudioManager

enum class VolumeStreamType(val value: Int) {
    MUSIC(AudioManager.STREAM_MUSIC),
    ALARM(AudioManager.STREAM_ALARM),
    SYSTEM(AudioManager.STREAM_SYSTEM),
    UNKNOWN(-1)
}