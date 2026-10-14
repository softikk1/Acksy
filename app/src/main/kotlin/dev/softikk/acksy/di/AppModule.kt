package dev.softikk.acksy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dev.softikk.acksy.ui.viewmodels.AuthViewModel
import dev.softikk.acksy.ui.viewmodels.HabitsViewModel
import dev.softikk.acksy.ui.viewmodels.SettingsViewModel
import dev.softikk.acksy.ui.viewmodels.StatisticsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModule = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().filesDir.resolve("dataStoreFileName") }
        )
    }

    viewModel<AuthViewModel>()
    viewModel<HabitsViewModel>()
    viewModel<SettingsViewModel>()
    viewModel<StatisticsViewModel>()
}