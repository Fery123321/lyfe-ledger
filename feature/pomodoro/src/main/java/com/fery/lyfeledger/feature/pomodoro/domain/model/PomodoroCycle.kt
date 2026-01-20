package com.fery.lyfeledger.feature.pomodoro.domain.model

data class PomodoroCycle(
    val id: String,
    val workSessionsCompleted: Int,
    val totalCycles: Int = 4
)