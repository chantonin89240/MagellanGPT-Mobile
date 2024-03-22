package com.p3g3.magellangpt.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
internal data class ConversationResponse(
    val role: String = "assistant",
    val content: String
)

