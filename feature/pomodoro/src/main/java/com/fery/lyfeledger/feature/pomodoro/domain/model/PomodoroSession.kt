package com.fery.lyfeledger.feature.pomodoro.domain.model

import com.fery.lyfeledger.feature.pomodoro.domain.enum.SessionType

data class PomodoroSession(
    val id: String,
    val type: SessionType,
    val durationSeconds: Long,
    val remainingSeconds: Long,
    val isActive: Boolean,
    val startTime: Long,
    val taskId: String? = null // Links to task for logging
) {

}