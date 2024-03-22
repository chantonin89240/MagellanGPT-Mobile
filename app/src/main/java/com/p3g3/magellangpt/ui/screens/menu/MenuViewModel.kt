package com.p3g3.magellangpt.ui.screens.menu

import android.app.Application
import com.p3g3.magellangpt.domain.models.conversation.Conversation
import com.p3g3.magellangpt.ui.core.ViewModel


class MenuViewModel(application: Application) : ViewModel<MenuState>(MenuState(), application) {



}


data class MenuState(
    val isLoading: Boolean = true,
    val conversations: List<Conversation> = emptyList(),
    val error: String? = null
)