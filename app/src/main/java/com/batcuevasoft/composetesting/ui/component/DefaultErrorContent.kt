package com.batcuevasoft.composetesting.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultErrorContent(
    title: String,
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onButtonPressed: () -> Unit
) {
    // Missing adding some warning sign to this composable
    TitleDescriptionButtonContent(
        title = title,
        description = description,
        buttonText = buttonText,
        modifier = modifier,
        onButtonPressed = onButtonPressed
    )
}