package com.p3g3.magellangpt.domain.repositories

import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.message.Message

interface MessageRepository {
    suspend fun send(message: Message) : MessageResponse
}