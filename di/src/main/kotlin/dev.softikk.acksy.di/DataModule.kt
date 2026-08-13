package dev.softikk.acksy.di

import dev.softikk.acksy.data.sources.AuthRemoteSource
import dev.softikk.acksy.data.sources.HabitsRemoteSource
import dev.softikk.acksy.data.sources.SettingsRemoteSource
import dev.softikk.acksy.data.sources.StatisticsRemoteSource
import dev.softikk.acksy.data.sources.remote.AuthRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.HabitsRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.SettingsRemoteSourceImpl
import dev.softikk.acksy.data.sources.remote.StatisticsRemoteSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
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
                bearer {
                    TODO()
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
}