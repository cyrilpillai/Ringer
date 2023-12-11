package com.cyrilpillai.ringer.mode.repository

import android.content.Context
import com.cyrilpillai.ringer.R
import com.cyrilpillai.ringer.mode.model.ModeItem
import com.cyrilpillai.ringer.repository.model.ModeType.NORMAL
import com.cyrilpillai.ringer.repository.model.ModeType.SILENT
import com.cyrilpillai.ringer.repository.model.ModeType.VIBRATE

fun getModeItems(
    context: Context,
    isNotificationPolicyAccessGranted: Boolean
): List<ModeItem> {
    return listOf(
        ModeItem(
            type = NORMAL,
            title = context.getString(R.string.mode_normal_title),
            iconResource = R.drawable.ic_normal_mode,
            isEnabled = isNotificationPolicyAccessGranted
        ),
        ModeItem(
            type = VIBRATE,
            title = context.getString(R.string.mode_vibrate_title),
            iconResource = R.drawable.ic_vibrate_mode,
            isEnabled = isNotificationPolicyAccessGranted
        ),
        ModeItem(
            type = SILENT,
            title = context.getString(R.string.mode_silent_title),
            iconResource = R.drawable.ic_silent_mode,
            isEnabled = isNotificationPolicyAccessGranted
        )
    )
}