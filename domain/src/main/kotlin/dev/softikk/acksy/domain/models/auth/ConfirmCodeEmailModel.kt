package dev.softikk.acksy.domain.models.auth

import kotlin.uuid.Uuid

data class ConfirmCodeEmailModel(
    val tempToken: Uuid
)
