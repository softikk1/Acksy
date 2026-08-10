package dev.softikk.acksy.data.sources.remote.utils

import dev.softikk.acksy.data.models.ErrorModel
import dev.softikk.acksy.data.models.Response
import dev.softikk.acksy.dev.softikk.acksy.entities.MessageDto
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend fun getFailedResponse(result: HttpResponse): Response.Failed {
    return Response.Failed(
        ErrorModel(
            code = result.status, message = result.body<MessageDto>().message
        )
    )
}