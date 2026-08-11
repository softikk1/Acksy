package dev.softikk.acksy.domain.models.auth

import kotlin.uuid.Uuid

data class TokensModel(
    val access: String,
    val refresh: Uuid
)
