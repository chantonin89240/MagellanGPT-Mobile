package com.p3g3.magellangpt.data.remote

import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.message.Message
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

internal class MessageAPI(private val client: HttpClient) {


    suspend fun send(message: Message): MessageResponse? {
        val response: HttpResponse = client.post("/api/OpenAI/chat") {
            contentType(ContentType.Application.Json)
            setBody(Message("gpt3", message.requestMessage))
        }
        var messageResponse: MessageResponse? = null
        if (response.status == HttpStatusCode.OK) {
            val responseBody: String = response.bodyAsText()

            // Analysez le corps de la réponse comme un JsonElement.
            val jsonElement = Json.parseToJsonElement(responseBody)

            // Vérifiez si l'élément JSON est un objet ou un tableau.
            if (jsonElement is JsonArray) {
                // Si c'est un tableau, vous pouvez le traiter directement.
                val concatResponse = jsonElement.joinToString(separator = "") {
                    it.jsonPrimitive.contentOrNull ?: ""
                }
                messageResponse = MessageResponse("assistant", concatResponse)

            } else if (jsonElement is JsonObject) {
                // Si c'est un objet, et que vous vous attendez à un tableau dans 'data',
                // alors procédez comme suit.
                val data = jsonElement.jsonObject["data"]?.jsonArray
                val concatResponse = data?.joinToString(separator = "") {
                    it.jsonPrimitive.contentOrNull ?: ""
                } ?: ""
               messageResponse = MessageResponse("assistant", concatResponse)
            }
            // Faites ici ce que vous voulez avec concatResponse.
        } else {
            println("Erreur: ${response.status}")
        }

        return messageResponse
    }


}

