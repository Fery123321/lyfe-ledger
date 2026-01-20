package com.fery.lyfeledger.feature.pomodoro.domain.usecase

import com.fery.lyfeledger.feature.pomodoro.domain.enum.SessionType
import com.fery.lyfeledger.feature.pomodoro.domain.model.PomodoroSession
import com.fery.lyfeledger.feature.pomodoro.domain.repository.PomodoroRepository

class CompleteSessionUseCase(
    private val repo: PomodoroRepository,
    private val startSessionUseCase: StartPomodoroUseCase  // Inject dependency
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return repo.getActiveSession().fold(
            onSuccess = { session ->
                if (session?.id != id) {
                    return Result.failure(Exception("Session ID mismatch"))
                }

                repo.endSession(id).fold(
                    onSuccess = {
                        handleTransition(session)
                    },
                    onFailure = { Result.failure(it) }
                )
            },
            onFailure = { Result.failure(it) }
        )
    }

    private suspend fun handleTransition(session: PomodoroSession): Result<Unit> {
        return when (session.type) {
            SessionType.WORK -> {
                repo.getCurrentCycle(session.taskId).fold(
                    onSuccess = { cycle ->
                        val nextSessionType = if (cycle.workSessionsCompleted < 4) {
                            SessionType.SHORT_BREAK
                        } else {
                            SessionType.LONG_BREAK
                        }

                        startSessionUseCase(nextSessionType, session.taskId).fold(
                            onSuccess = {
                                if (cycle.workSessionsCompleted >= 4) {
                                    repo.logCycle(cycle.copy(workSessionsCompleted = 0))
                                }
                                Result.success(Unit)
                            },
                            onFailure = { Result.failure(it) }
                        )
                    },
                    onFailure = { Result.failure(it) }
                )
            }
            else -> {
                // Break ended → Next work session
                startSessionUseCase(SessionType.WORK, session.taskId).fold(
                    onSuccess = { Result.success(Unit) },
                    onFailure = { Result.failure(it) }
                )
            }
        }
    }
}
