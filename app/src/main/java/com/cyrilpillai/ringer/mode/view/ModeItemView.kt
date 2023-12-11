package com.cyrilpillai.ringer.mode.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.cyrilpillai.ringer.mode.model.ModeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeItemView(
    modeItem: ModeItem,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        enabled = enabled,
        onClick = onClick,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLeadingIconColor = Color.White,
            selectedLabelColor = Color.White

        ),
        label = {
            Text(
                modeItem.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = modeItem.iconResource),
                contentDescription = modeItem.title,
                modifier = Modifier
                    .size(FilterChipDefaults.IconSize)
            )
        },
        modifier = modifier
    )
}