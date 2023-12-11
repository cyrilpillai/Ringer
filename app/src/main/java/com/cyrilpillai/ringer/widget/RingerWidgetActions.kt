package com.cyrilpillai.ringer.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.repository.model.ModeType.NORMAL
import com.cyrilpillai.ringer.repository.model.ModeType.SILENT
import com.cyrilpillai.ringer.repository.model.ModeType.VIBRATE
import com.cyrilpillai.ringer.repository.model.VolumeStreamType.MUSIC

class IncreaseVolumeAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        AudioManagerRepo.get(context).increaseStreamVolume(MUSIC)
        RingerWidget().update(context, glanceId)
    }
}

class DecreaseVolumeAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        AudioManagerRepo.get(context).decreaseStreamVolume(MUSIC)
        RingerWidget().update(context, glanceId)
    }
}

class NormalModeAction(
) : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        AudioManagerRepo.get(context).setRingerMode(NORMAL)
        RingerWidget().update(context, glanceId)
    }
}

class VibrateModeAction(
) : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        AudioManagerRepo.get(context).setRingerMode(VIBRATE)
        RingerWidget().update(context, glanceId)
    }
}

class SilentModeAction(
) : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        AudioManagerRepo.get(context).setRingerMode(SILENT)
        RingerWidget().update(context, glanceId)
    }
}