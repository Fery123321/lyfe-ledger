package com.fery.lyfeledger.feature.pomodoro.domain.repository

import com.fery.lyfeledger.feature.pomodoro.domain.model.PomodoroCycle
import com.fery.lyfeledger.feature.pomodoro.domain.model.PomodoroSession
import com.fery.lyfeledger.feature.pomodoro.domain.model.PomodoroStats

interface PomodoroRepository {
    suspend fun startSession(session: PomodoroSession): Result<PomodoroSession>
    suspend fun pauseSession(id: String): Result<PomodoroSession?>
    suspend fun endSession(id: String): Result<Unit>
    suspend fun getActiveSession(): Result<PomodoroSession?>
    suspend fun logCycle(cycle: PomodoroCycle): Result<Unit>
    suspend fun getStats(date: String): Result<PomodoroStats?>
    suspend fun getCustomDurations(): Result<CustomDurations>
    suspend fun saveCustomDurations(durations: CustomDurations): Result<Unit>
    suspend fun getCurrentCycle(taskId: String?): Result<PomodoroCycle>

    data class CustomDurations(
        val workSeconds: Long = 25 * 60,
        val shortBreakSeconds: Long = 5 * 60,
        val longBreakSeconds: Long = 30 * 60
    )
}
