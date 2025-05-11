package com.example.smartroutineapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class NavigableGraph {

    sealed class BottomNavItem {
        @Serializable
        data object Home : BottomNavItem()

        @Serializable
        data object List : BottomNavItem()

        @Serializable
        data object User : BottomNavItem()

        companion object {
            val toList = listOf(Home, List, User)
        }
    }

    @Serializable
    data object Main : NavigableGraph()

    @Serializable
    data class Task(val name: String) : NavigableGraph()
}