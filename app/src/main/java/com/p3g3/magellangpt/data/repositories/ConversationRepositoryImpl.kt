package com.p3g3.magellangpt.data.repositories

import android.content.Context
import com.p3g3.magellangpt.data.remote.MessageAPI
import com.p3g3.magellangpt.domain.models.conversation.Conversation
import com.p3g3.magellangpt.domain.repositories.ConversationRepository
import kotlinx.coroutines.flow.Flow

internal class ConversationRepositoryImpl(
    private val  context: Context,
    private val messageAPI: MessageAPI
) : ConversationRepository {
    override suspend fun getConversations(): Flow<List<Conversation>> {
        TODO("Not yet implemented")
    }

    override suspend fun getConversation(id: Int): Conversation {
        TODO("Not yet implemented")
    }
}