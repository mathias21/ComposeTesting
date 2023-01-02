package com.batcuevasoft.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigate(destination: NavigationDestination) {
    navigate(destination.name)
}