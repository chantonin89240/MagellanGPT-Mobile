package com.p3g3.magellangpt.data.repositories

import com.p3g3.magellangpt.data.remote.MessageAPI
import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.message.Message
import com.p3g3.magellangpt.domain.repositories.MessageRepository

internal class MessageRepositoryImpl(
    private val messageAPI: MessageAPI,
) : MessageRepository {

    override suspend fun send(message: Message): MessageResponse {
        val response = messageAPI.send(message)

        return if (response != null) {
            MessageResponse(response.role, response.content)
        } else {
            // Renvoie une instance par défaut ou des valeurs spécifiques indiquant une réponse nulle.
            MessageResponse("assistant", "Pas de réponse")
        }
    }
}
