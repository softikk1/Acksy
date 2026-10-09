package dev.softikk.acksy.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavController() {
    val backStack = rememberNavBackStack(Routes.Welcome)
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    Routes.Welcome -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.EnterEmail -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.EnterVerifCode -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.Home -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.CreateHabit -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.HabitDetails -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.Statistics -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.SelectLanguage -> NavEntry(
                        key = key
                    ) {

                    }

                    Routes.Settings -> NavEntry(
                        key = key
                    ) {

                    }

                    else -> NavEntry(
                        key = key
                    ) {

                    }
                }
            })
    }
}