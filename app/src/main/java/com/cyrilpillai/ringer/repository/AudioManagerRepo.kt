package com.cyrilpillai.ringer.repository

import android.content.Context
import android.media.AudioManager
import com.cyrilpillai.ringer.repository.model.ModeType
import com.cyrilpillai.ringer.repository.model.ModeType.NORMAL
import com.cyrilpillai.ringer.repository.model.ModeType.SILENT
import com.cyrilpillai.ringer.repository.model.ModeType.UNKNOWN
import com.cyrilpillai.ringer.repository.model.ModeType.VIBRATE
import com.cyrilpillai.ringer.repository.model.VolumeStreamType
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.SYSTEM

class AudioManagerRepo(
    private val audioManager: AudioManager
) {
    fun getStreamVolume(volumeStreamType: VolumeStreamType): Int {
        return audioManager.getStreamVolume(volumeStreamType.value)
    }

    fun getStreamMinVolume(volumeStreamType: VolumeStreamType): Int {
        return if (volumeStreamType == SYSTEM) 1 else 0
    }

    fun getStreamMaxVolume(volumeStreamType: VolumeStreamType): Int {
        return audioManager.getStreamMaxVolume(volumeStreamType.value)
    }

    fun setStreamVolume(volumeStreamType: VolumeStreamType, volume: Int) {
        audioManager.setStreamVolume(volumeStreamType.value, volume, 0)
    }

    fun increaseStreamVolume(volumeStreamType: VolumeStreamType) {
        audioManager.adjustStreamVolume(volumeStreamType.value, AudioManager.ADJUST_RAISE, 0)
    }

    fun decreaseStreamVolume(volumeStreamType: VolumeStreamType) {
        audioManager.adjustStreamVolume(volumeStreamType.value, AudioManager.ADJUST_LOWER, 0)
    }

    fun getRingerMode(): ModeType {
        return when (audioManager.ringerMode) {
            AudioManager.RINGER_MODE_NORMAL -> NORMAL
            AudioManager.RINGER_MODE_VIBRATE -> VIBRATE
            AudioManager.RINGER_MODE_SILENT -> SILENT
            else -> UNKNOWN
        }
    }

    fun setRingerMode(modeType: ModeType) {
        when (modeType) {
            NORMAL -> audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            VIBRATE -> audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
            SILENT -> audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
            else -> UNKNOWN
        }
    }

    companion object {
        @Volatile
        private var mInstance: AudioManagerRepo? = null

        fun get(context: Context): AudioManagerRepo =
            mInstance ?: synchronized(this) {
                val newInstance = mInstance
                    ?: AudioManagerRepo(
                        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    ).also {
                        mInstance = it
                    }
                newInstance
            }
    }
}