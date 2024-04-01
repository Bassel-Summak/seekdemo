package com.basapps.seekdemo.common.domain.usecases

import javax.inject.Inject
import com.basapps.seekdemo.storage.SessionHandler

class LogoutUseCase @Inject constructor(
    private val sessionHandler: SessionHandler,
) {
    suspend fun invoke() {
        sessionHandler.clear()
    }
}
