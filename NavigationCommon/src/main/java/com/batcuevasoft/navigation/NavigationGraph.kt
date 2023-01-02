package com.batcuevasoft.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

abstract class NavigationGraph(
    val name: String,
    private val block: NavGraphBuilder.(NavHostController) -> Unit
) {

    abstract val startDestination: NavigationDestination

    fun buildGraph(builder: NavGraphBuilder, controller: NavHostController) {
        builder.navigation(startDestination = startDestination.name, route = name) {
            block(controller)
        }
    }
}