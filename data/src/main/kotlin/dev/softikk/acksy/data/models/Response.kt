package dev.softikk.acksy.data.models

sealed interface Response<out S, out F> {
    data class Success<T>(val value: T) : Response<T, Nothing>
    data class Failed(val value: ErrorModel) : Response<Nothing, ErrorModel>
}