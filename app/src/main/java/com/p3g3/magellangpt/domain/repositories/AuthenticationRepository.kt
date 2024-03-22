package com.p3g3.magellangpt.domain.repositories

import android.app.Activity
import com.p3g3.magellangpt.domain.models.user.User

interface AuthenticationRepository {

    fun login(activity : Activity, onSuccess : () -> Unit, onCancel : () -> Unit, onError : () -> Unit)

    suspend fun logout()

    suspend fun userConnected(onResult : (Boolean) -> Unit)

    suspend fun getCurrentUser(onResult :(user : User) -> Unit)
}