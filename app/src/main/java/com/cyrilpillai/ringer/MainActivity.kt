package com.cyrilpillai.ringer

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyrilpillai.ringer.common.view.HeaderView
import com.cyrilpillai.ringer.mode.view.ModeView
import com.cyrilpillai.ringer.repository.AudioManagerRepo
import com.cyrilpillai.ringer.volume.repository.getVolumeItems
import com.cyrilpillai.ringer.volume.view.VolumeItemView

class MainActivity : ComponentActivity() {

    private lateinit var audioManagerRepo: AudioManagerRepo
    private lateinit var notificationManager: NotificationManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSystemService()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = getString(R.string.app_name))
                    })
                }) {
                MainScreen(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                )
            }
        }
    }

    private fun initSystemService() {
        audioManagerRepo = AudioManagerRepo.get(this.applicationContext)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    if (intent.action == AudioManager.RINGER_MODE_CHANGED_ACTION) {
                        Log.d("Ringer", "Ringer mode changed")
                    }
                }
            },
            IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION)
        )
    }


    @Composable
    private fun MainScreen(
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            addVolumeView(this)
            addModeView(this)
        }
    }

    private fun addVolumeView(lazyListScope: LazyListScope) {
        with(lazyListScope) {
            item {
                HeaderView(
                    title = stringResource(R.string.volume_header_title),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            items(getVolumeItems(this@MainActivity.applicationContext)) {
                VolumeItemView(
                    audioManagerRepo = audioManagerRepo,
                    volumeItem = it,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }

    private fun addModeView(lazyListScope: LazyListScope) {
        with(lazyListScope) {
            item {
                HeaderView(
                    title = stringResource(R.string.mode_header_title),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                ModeView(
                    audioManagerRepo = audioManagerRepo,
                    isNotificationPolicyAccessGranted = notificationManager.isNotificationPolicyAccessGranted,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            if (!notificationManager.isNotificationPolicyAccessGranted) {
                item {
                    Button(
                        onClick = {
                            startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
                        }) {
                        Text(text = "Grant permission")
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun MainScreenPreview() {
        MainScreen()
    }
}