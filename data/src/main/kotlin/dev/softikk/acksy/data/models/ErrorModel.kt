package dev.softikk.acksy.data.models

import io.ktor.http.HttpStatusCode

data class ErrorModel(
    val code: HttpStatusCode,
    val message: String
)
