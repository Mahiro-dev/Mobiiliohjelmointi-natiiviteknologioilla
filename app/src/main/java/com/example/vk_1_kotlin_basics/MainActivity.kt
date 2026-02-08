package com.example.vk_1_kotlin_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vk_1_kotlin_basics.ui.theme.Vk1kotlinBasicsTheme
import com.example.vk_1_kotlin_basics.view.CalendarScreen
import com.example.vk_1_kotlin_basics.view.HomeScreen
import com.example.vk_1_kotlin_basics.view.SettingsScreen
import com.example.vk_1_kotlin_basics.viewmodel.AppViewModel
import com.example.vk_1_kotlin_basics.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState

const val ROUTE_HOME = "home"
const val ROUTE_CALENDAR = "calendar"
const val ROUTE_SETTINGS = "settings"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val taskVm: TaskViewModel = viewModel()
            val appVm: AppViewModel = viewModel()

            val darkTheme by appVm.darkTheme.collectAsState()

            Vk1kotlinBasicsTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME
                    ) {
                        composable(ROUTE_HOME) {
                            HomeScreen(
                                vm = taskVm,
                                navController = navController
                            )
                        }
                        composable(ROUTE_CALENDAR) {
                            CalendarScreen(
                                vm = taskVm,
                                navController = navController
                            )
                        }
                        composable(ROUTE_SETTINGS) {
                            SettingsScreen(
                                appVm = appVm,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
