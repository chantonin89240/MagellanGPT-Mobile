package com.p3g3.magellangpt.data.remote.responses

import kotlinx.serialization.Serializable


@Serializable
data class MessageResponse(
    val role: String = "assistant",
    val content: String
)