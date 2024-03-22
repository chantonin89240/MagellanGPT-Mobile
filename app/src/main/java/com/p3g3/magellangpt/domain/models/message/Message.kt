package com.p3g3.magellangpt.domain.models.message

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val model: String = "gpt3",
    val requestMessage: String,
)

enum class Role {
    user, assistant
}