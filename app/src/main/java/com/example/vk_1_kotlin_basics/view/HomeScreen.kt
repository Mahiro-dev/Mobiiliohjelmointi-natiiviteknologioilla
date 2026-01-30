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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_1_kotlin_basics.model.Task
import com.example.vk_1_kotlin_basics.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {
    val tasks by vm.tasks.collectAsState()

    var newTitle by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Vk 3: MVVM",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { vm.sortByDueDate() }) { Text("Sort") }
            Button(onClick = { vm.filterByDone(true) }) { Text("Done") }
            Button(onClick = { vm.filterByDone(false) }) { Text("Todo") }
            Button(onClick = { vm.clearFilter() }) { Text("All") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("New task title") },
                singleLine = true
            )

            Button(
                onClick = {
                    val trimmed = newTitle.trim()
                    if (trimmed.isNotEmpty()) {
                        vm.addTaskFromTitle(trimmed)
                        newTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
                TaskRow(
                    task = task,
                    onToggle = { vm.toggleDone(task.id) },
                    onRemove = { vm.removeTask(task.id) },
                    onOpenDetail = { selectedTask = task }
                )
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
private fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onRemove: () -> Unit,
    onOpenDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpenDetail() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge
                )

                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            TextButton(onClick = onRemove) {
                Text("Delete")
            }
        }
    }
}
