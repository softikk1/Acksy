package dev.softikk.acksy.data.sources

interface HabitsRemoteSource {
    fun getHabits()
    fun createHabit()
    fun confirmHabit()
    fun deleteHabit()
    fun habitDetails()
    fun deleteConfirmHabit()
}