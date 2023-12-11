package com.cyrilpillai.ringer.mode.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cyrilpillai.ringer.mode.repository.getModeItems
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.repository.model.ModeType.NORMAL
import com.cyrilpillai.ringer.repository.model.ModeType.SILENT
import com.cyrilpillai.ringer.repository.model.ModeType.UNKNOWN
import com.cyrilpillai.ringer.repository.model.ModeType.VIBRATE

@Composable
fun ModeView(
    audioManagerRepo: AudioManagerRepo,
    isNotificationPolicyAccessGranted: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        var normalState by remember {
            mutableStateOf(audioManagerRepo.getRingerMode() == NORMAL)
        }

        var silentState by remember {
            mutableStateOf(audioManagerRepo.getRingerMode() == SILENT)
        }
        var vibrateState by remember {
            mutableStateOf(audioManagerRepo.getRingerMode() == VIBRATE)
        }

        getModeItems(
            context = LocalContext.current,
            isNotificationPolicyAccessGranted = isNotificationPolicyAccessGranted
        ).forEach {
            ModeItemView(
                modeItem = it,
                selected = when (it.type) {
                    NORMAL -> normalState
                    VIBRATE -> vibrateState
                    SILENT -> silentState
                    UNKNOWN -> false
                },
                enabled = it.isEnabled,
                onClick = {
                    when (it.type) {
                        NORMAL -> {
                            normalState = true
                            vibrateState = false
                            silentState = false
                        }

                        VIBRATE -> {
                            normalState = false
                            vibrateState = true
                            silentState = false
                        }

                        SILENT -> {
                            normalState = false
                            vibrateState = false
                            silentState = true
                        }

                        else -> Unit
                    }
                    audioManagerRepo.setRingerMode(it.type)
                },
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}