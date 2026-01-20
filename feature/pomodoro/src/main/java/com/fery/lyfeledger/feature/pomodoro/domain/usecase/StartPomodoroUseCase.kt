package com.fery.lyfeledger.feature.pomodoro.domain.usecase

import com.fery.lyfeledger.feature.pomodoro.domain.enum.SessionType
import com.fery.lyfeledger.feature.pomodoro.domain.model.PomodoroSession
import com.fery.lyfeledger.feature.pomodoro.domain.repository.PomodoroRepository
import java.util.UUID

class StartPomodoroUseCase(
    private val repo: PomodoroRepository
) {
    suspend operator fun invoke(
        type: SessionType = SessionType.WORK,
        taskId: String? = null
    ): Result<PomodoroSession> {
        return repo.getCustomDurations()
            .fold(
                onSuccess = { durations ->
                    val session = PomodoroSession(
                        id = UUID.randomUUID().toString(),
                        type = SessionType.WORK,
                        durationSeconds = durations.workSeconds,
                        remainingSeconds = durations.workSeconds,
                        isActive = true,
                        startTime = System.currentTimeMillis(),
                        taskId = taskId
                    )
                    repo.startSession(session)
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )

    }
}