package com.cyrilpillai.ringer.common.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HeaderView(
    title: String,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        style = style
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderViewPreview() {
    HeaderView(title = "Volume")
}