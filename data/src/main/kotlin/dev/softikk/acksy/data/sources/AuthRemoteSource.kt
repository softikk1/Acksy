package dev.softikk.acksy.data.sources

import dev.softikk.acksy.data.models.ErrorModel
import dev.softikk.acksy.data.models.Response
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.ConfirmCodeEmailRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.LoginRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RefreshRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.auth.RegisterRespondDto
import kotlin.uuid.Uuid

interface AuthRemoteSource {
    suspend fun sendCodeEmail(email: String): Response<Unit, ErrorModel>
    suspend fun confirmCodeEmail(email: String, code: String): Response<ConfirmCodeEmailRespondDto, ErrorModel>
    suspend fun login(email: String, tempToken: Uuid): Response<LoginRespondDto, ErrorModel>
    suspend fun register(email: String, tempToken: Uuid, username: String): Response<RegisterRespondDto, ErrorModel>
    suspend fun refresh(refresh: Uuid): Response<RefreshRespondDto, ErrorModel>
}