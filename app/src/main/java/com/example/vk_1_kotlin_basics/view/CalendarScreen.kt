package com.example.vk_1_kotlin_basics.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vk_1_kotlin_basics.model.Task
import com.example.vk_1_kotlin_basics.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState
import java.time.LocalDate

@Composable
fun CalendarScreen(
    vm: TaskViewModel,
    navController: NavController
) {
    val tasks by vm.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val grouped: Map<LocalDate, List<Task>> = tasks
        .groupBy { it.dueDate }
        .toSortedMap()

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
                text = "Calendar",
                style = MaterialTheme.typography.headlineSmall
            )

            TextButton(onClick = { navController.popBackStack() }) {
                Text("List")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            grouped.forEach { (date, dayTasks) ->
                item(key = "header-$date") {
                    Text(
                        text = date.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }

                items(dayTasks, key = { it.id }) { task ->
                    CalendarTaskRow(
                        task = task,
                        onOpenDetail = { selectedTask = task }
                    )
                }
            }
        }
    }

    if (selectedTask != null) {
        DetailDialog(
            task = selectedTask!!,
            onDismiss = { selectedTask = null },
            onSave = { updated ->
                vm.updateTask(updated)
                selectedTask = null
            },
            onDelete = { id ->
                vm.removeTask(id)
                selectedTask = null
            }
        )
    }
}

@Composable
private fun CalendarTaskRow(
    task: Task,
    onOpenDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpenDetail() }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge
            )

            if (task.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (task.done) "Done" else "Todo",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
