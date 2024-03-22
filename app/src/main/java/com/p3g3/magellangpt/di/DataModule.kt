package com.p3g3.magellangpt.di

import com.p3g3.magellangpt.data.remote.MessageAPI
import com.p3g3.magellangpt.data.remote.createHttpClient
import com.p3g3.magellangpt.data.repositories.MessageRepositoryImpl
import com.p3g3.magellangpt.domain.repositories.MessageRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

private const val OPENAPIURL = "https://apiappmagellangptg3dev.azurewebsites.net/api/OpenAI"

val dataModule = module {

    single<HttpClient> {
        createHttpClient(
            baseUrl = OPENAPIURL
        )
    }

    //single<RealmDatabase> { RMDatabase() }

    //single { CharacterLocal(get()) }

    single { MessageAPI(get()) }

    single<MessageRepository> { MessageRepositoryImpl(get()) }

}
