package dev.softikk.acksy.data.sources

interface SettingsRemoteSource {
    fun getSettings()
    fun refreshSettings()
}