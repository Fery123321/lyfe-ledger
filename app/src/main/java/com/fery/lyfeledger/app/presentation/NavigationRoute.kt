package com.fery.lyfeledger.app.presentation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable
    data object Task: NavigationRoute

    @Serializable
    data object Habit: NavigationRoute

    @Serializable
    data object Calendar: NavigationRoute

    @Serializable
    data object Pomodoro: NavigationRoute

    @Serializable
    data object Settings: NavigationRoute
}