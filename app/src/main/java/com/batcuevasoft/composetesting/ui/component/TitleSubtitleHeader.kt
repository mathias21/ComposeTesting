package com.batcuevasoft.composetesting.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleSubtitleHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(text = title, style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = subtitle, style = MaterialTheme.typography.bodyLarge)
    }
}