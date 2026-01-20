package com.fery.lyfeledger.feature.pomodoro.domain.model

data class PomodoroStats(
    val date: String, // YYYY-MM-DD
    val totalWorkMinutes: Int,
    val cyclesCompleted: Int,
    val longestStreak: Int
)