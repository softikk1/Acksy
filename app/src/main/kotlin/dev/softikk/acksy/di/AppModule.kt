package dev.softikk.acksy.di

import dev.softikk.acksy.ui.viewmodels.AuthViewModel
import dev.softikk.acksy.ui.viewmodels.HabitsViewModel
import dev.softikk.acksy.ui.viewmodels.SettingsViewModel
import dev.softikk.acksy.ui.viewmodels.StatisticsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModule = module {
    viewModel<AuthViewModel>()
    viewModel<HabitsViewModel>()
    viewModel<SettingsViewModel>()
    viewModel<StatisticsViewModel>()
}