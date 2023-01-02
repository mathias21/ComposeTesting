package com.batcuevasoft.composetesting.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleDescriptionButtonContent(
    title: String,
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    onButtonPressed: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleSubtitleHeader(
            title = title,
            subtitle = description
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            BottomNavigationButton(
                buttonText = buttonText,
                isButtonEnabled = isButtonEnabled,
                onButtonPressed = onButtonPressed
            )
        }
    }
}