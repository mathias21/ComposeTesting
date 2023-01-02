package com.batcuevasoft.composetesting.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationButton(
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    buttonText: String,
    onButtonPressed: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = isButtonEnabled,
            onClick = onButtonPressed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Text(text = buttonText, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}