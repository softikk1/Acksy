package dev.softikk.acksy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.softikk.acksy.ui.navigation.NavController
import dev.softikk.acksy.ui.theme.AcksyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcksyTheme {
                NavController()
            }
        }
    }
}