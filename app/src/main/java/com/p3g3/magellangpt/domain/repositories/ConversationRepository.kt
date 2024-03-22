package com.p3g3.magellangpt.domain.repositories

import com.p3g3.magellangpt.domain.models.conversation.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    suspend fun getConversations(): Flow<List<Conversation>>

    suspend fun getConversation(id: Int): Conversation


}