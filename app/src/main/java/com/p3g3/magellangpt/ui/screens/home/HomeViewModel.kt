package com.p3g3.magellangpt.ui.screens.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.conversation.Conversation
import com.p3g3.magellangpt.domain.models.message.Message
import com.p3g3.magellangpt.domain.repositories.MessageRepository
import com.p3g3.magellangpt.ui.core.Destination
import com.p3g3.magellangpt.ui.core.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

sealed interface HomeAction {
    data class SelectedConversation(val conversation: Conversation) : HomeAction
    data class SendMessage(val message: Message) : HomeAction
    class SelectedMenu : HomeAction
}

class HomeViewModel(application: Application) : ViewModel<HomeState>(HomeState(), application) {

    private val messageRepository: MessageRepository by inject()

    private val _messagesResponses = MutableStateFlow(listOf<MessageResponse>())
    val messagesResponses = _messagesResponses.asStateFlow()

    private val _messages = MutableStateFlow(listOf<Message>())
    val messages = _messages.asStateFlow()

    private val _conversations = MutableStateFlow<List<Any>>(emptyList())
    val conversations = _conversations.asStateFlow()

    /*private val _messageResponse = MutableLiveData<MessageResponse?>()
    val messageResponse: LiveData<MessageResponse?> = _messageResponse*/

    private val _state = MutableLiveData<HomeState>()

    init {
        _state.value = HomeState()
    }

    fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.SelectedMenu -> selectedMenu()
            is HomeAction.SendMessage -> sendMessage(action.message)
            //is HomeAction.SendMessage -> sendMessage()
            else -> {}
        }
    }

    private fun selectedMenu() =
        sendEvent(Destination.Menu())

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            // Afficher un indicateur de chargement si nécessaire
            //_state.value = _state.value?.copy(isLoading = false)
            _conversations.update { currentList ->
                currentList + listOf(message)
            }
            val response = messageRepository.send(message)
            _conversations.update { currentList ->
                currentList + listOf(response)
            }
            addMessageResponse(MessageResponse(response.role, response.content))
            //_messageResponse.postValue(response) // Post la réponse pour l'observer dans l'UI
        }
    }
    fun addMessageResponse(messageReponse: MessageResponse) {
        val updatedList = _messagesResponses.value.toMutableList().apply {
            add(messageReponse)
        }
        _messagesResponses.value = updatedList
    }
    fun addMessageSend(message: Message) {
        val updatedList = _messages.value.toMutableList().apply {
            add(message)
        }
        _messages.value = updatedList
    }
    fun getConv(): Conversation {
        return Conversation(_messages, _messagesResponses)
    }
}


data class HomeState(
    val isLoading: Boolean = true,
    val error: String? = null
)

