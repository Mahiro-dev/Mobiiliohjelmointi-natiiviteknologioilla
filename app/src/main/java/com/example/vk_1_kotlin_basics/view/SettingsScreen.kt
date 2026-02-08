package com.example.vk_1_kotlin_basics.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vk_1_kotlin_basics.viewmodel.AppViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun SettingsScreen(
    appVm: AppViewModel,
    navController: NavController
) {
    val darkTheme by appVm.darkTheme.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineSmall
            )

            TextButton(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark theme")
                Switch(
                    checked = darkTheme,
                    onCheckedChange = { appVm.setDarkTheme(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}
