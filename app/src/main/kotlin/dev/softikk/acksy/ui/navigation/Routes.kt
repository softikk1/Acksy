package dev.softikk.acksy.ui.navigation

import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.ui.models.HabitAppModel
import dev.softikk.acksy.ui.models.HabitDetailsAppModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavBar

@Serializable
sealed class Routes {
    @Serializable
    object Welcome : Routes(), NavKey

    @Serializable
    object EnterEmail : Routes(), NavKey

    @Serializable
    object EnterVerifCode : Routes(), NavKey

    @Serializable
    object Home : Routes(), NavBar, NavKey

    @Serializable
    object CreateHabit : Routes(), NavKey

    @Serializable
    data class HabitDetails(
        val habitModel: HabitAppModel,
        val habitDetailsAppModel: HabitDetailsAppModel
    ) : Routes(), NavKey

    @Serializable
    object Statistics : Routes(), NavBar, NavKey

    @Serializable
    object Settings : Routes(), NavBar, NavKey

    @Serializable
    object SelectLanguage : Routes(), NavKey
}