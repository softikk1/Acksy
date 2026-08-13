package dev.softikk.acksy.di

import dev.softikk.acksy.data.repository.AuthRepositoryImpl
import dev.softikk.acksy.data.repository.HabitsRepositoryImpl
import dev.softikk.acksy.data.repository.SettingsRepositoryImpl
import dev.softikk.acksy.data.repository.StatisticsRepositoryImpl
import dev.softikk.acksy.domain.repository.AuthRepository
import dev.softikk.acksy.domain.repository.HabitsRepository
import dev.softikk.acksy.domain.repository.SettingsRepository
import dev.softikk.acksy.domain.repository.StatisticsRepository
import org.koin.dsl.module

val domainModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
    single<HabitsRepository> {
        HabitsRepositoryImpl(get())
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }
    single<StatisticsRepository> {
        StatisticsRepositoryImpl(get())
    }
}