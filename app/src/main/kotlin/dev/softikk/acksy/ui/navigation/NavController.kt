package dev.softikk.acksy.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.softikk.acksy.ui.components.AcksyNavBar
import dev.softikk.acksy.ui.screens.CreateHabitScreen
import dev.softikk.acksy.ui.screens.EnterEmailScreen
import dev.softikk.acksy.ui.screens.EnterVerifCodeScreen
import dev.softikk.acksy.ui.screens.HabitDetailsScreen
import dev.softikk.acksy.ui.screens.HomeScreen
import dev.softikk.acksy.ui.screens.SelectLanguageScreen
import dev.softikk.acksy.ui.screens.SettingScreen
import dev.softikk.acksy.ui.screens.StatisticsScreen
import dev.softikk.acksy.ui.screens.WelcomeScreen
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.viewmodels.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavController() {
    val backStack = rememberNavBackStack(Routes.Welcome)
    val authViewModel = koinViewModel<AuthViewModel>()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface, bottomBar = {
            if (backStack.lastOrNull() is NavBar) {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    AcksyNavBar(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(bottom = Dimens.Paddings.mediumPadding), backStack = backStack
                    )
                }
            }
        }) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            backStack = backStack,
            onBack = dropUnlessResumed { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    Routes.Welcome -> NavEntry(
                        key = key
                    ) {
                        WelcomeScreen(
                            authViewModel = authViewModel, backStack = backStack
                        )
                    }

                    Routes.EnterEmail -> NavEntry(
                        key = key
                    ) {
                        EnterEmailScreen(
                            backStack = backStack
                        )
                    }

                    Routes.EnterVerifCode -> NavEntry(
                        key = key
                    ) {
                        EnterVerifCodeScreen(
                            backStack = backStack
                        )
                    }

                    Routes.Home -> NavEntry(
                        key = key
                    ) {
                        HomeScreen(
                            backStack = backStack, innerPadding = innerPadding
                        )
                    }

                    Routes.CreateHabit -> NavEntry(
                        key = key
                    ) {
                        CreateHabitScreen(
                            backStack = backStack
                        )
                    }

                    is Routes.HabitDetails -> NavEntry(
                        key = key
                    ) {
                        HabitDetailsScreen(
                            habitModel = key.habitModel,
                            habitDetailsAppModel = key.habitDetailsAppModel,
                            backStack = backStack
                        )
                    }

                    Routes.Statistics -> NavEntry(
                        key = key
                    ) {
                        StatisticsScreen(
                            backStack = backStack, innerPadding = innerPadding
                        )
                    }

                    Routes.SelectLanguage -> NavEntry(
                        key = key
                    ) {
                        SelectLanguageScreen(
                            backStack = backStack
                        )
                    }

                    Routes.Settings -> NavEntry(
                        key = key
                    ) {
                        SettingScreen(
                            backStack = backStack
                        )
                    }

                    else -> NavEntry(
                        key = key
                    ) {
                        Text(
                            text = "404"
                        )
                    }
                }
            })
    }
}