package com.p3g3.magellangpt.ui.screens.login

import android.app.Application
import com.p3g3.magellangpt.domain.repositories.ConversationRepository
import com.p3g3.magellangpt.ui.core.Destination
import com.p3g3.magellangpt.ui.core.ViewModel
import org.koin.core.component.inject

sealed interface LoginAction {
    /*data class SelectedConversation(val conversation: Conversation): HomeAction
    class SelectedMenu: HomeAction*/
    class Login: LoginAction
}
class LoginViewModel(application: Application) : ViewModel<LoginState>(LoginState(), application) {

    private val homeRepository: ConversationRepository by inject()

    /*init {
        fetchData(
            source = {homeRepository.getConversation(1)}
        ) {
            onSuccess {
            }
        }
    }*/

    fun handleAction(action: LoginAction) {
        when(action) {
            is LoginAction.Login -> login()
            else -> {}
        }
    }

    private fun login() =
        sendEvent(Destination.Menu())
}



data class LoginState(
    val isLoading: Boolean = true,
    val error: String? = null
)