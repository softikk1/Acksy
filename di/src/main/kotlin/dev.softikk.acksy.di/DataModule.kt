package dev.softikk.acksy.di

import dev.softikk.acksy.data.sources.AuthRemoteSource
import dev.softikk.acksy.data.sources.HabitsRemoteSource
import dev.softikk.acksy.data.sources.SettingsRemoteSource
import dev.softikk.acksy.data.sources.StatisticsRemoteSource
import dev.softikk.acksy.data.sources.TokensLocalSource
import dev.softikk.acksy.data.sources.local.TokensLocalSourceImpl
import dev.softikk.acksy.data.sources.remote.AuthRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.HabitsRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.SettingsRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.StatisticsRemoteSourceImpl
import dev.softikk.acksy.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            install(Auth) {
                val authRepository = get<AuthRepository>()
                val tokensLocalSource = get<TokensLocalSource>()
                bearer {
                    loadTokens {
                        tokensLocalSource.getRefreshToken()?.let { tokensModel ->
                            BearerTokens(
                                accessToken = tokensModel.access,
                                refreshToken = tokensModel.refresh.toString()
                            )
                        }
                    }
                    refreshTokens {
                        tokensLocalSource.getRefreshToken()?.let {
                            authRepository.refresh(it.refresh)
                        }
                        tokensLocalSource.getRefreshToken()?.let { tokensModel ->
                            BearerTokens(
                                accessToken = tokensModel.access,
                                refreshToken = tokensModel.refresh.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    single<AuthRemoteSource> {
        AuthRemoteSourceImpl(get())
    }
    single<HabitsRemoteSource> {
        HabitsRemoteSourceImpl(get())
    }
    single<SettingsRemoteSource> {
        SettingsRemoteSourceImpl(get())
    }
    single<StatisticsRemoteSource> {
        StatisticsRemoteSourceImpl(get())
    }

    single<TokensLocalSource> {
        TokensLocalSourceImpl(get())
    }
}