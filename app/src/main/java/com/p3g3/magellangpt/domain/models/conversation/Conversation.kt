package com.p3g3.magellangpt.domain.models.conversation

import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.message.Message
import kotlinx.coroutines.flow.MutableStateFlow

data class Conversation(
    val messages: MutableStateFlow<List<Message>>,
    val messageResponses: MutableStateFlow<List<MessageResponse>>
)