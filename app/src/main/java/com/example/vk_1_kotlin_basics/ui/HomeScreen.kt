package com.example.vk_1_kotlin_basics.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_1_kotlin_basics.viewmodel.TaskViewModel

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {
    var newTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Vk 2: Tasks",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { vm.sortByDueDate() }) { Text("Sort") }
            Button(onClick = { vm.filterByDone(true) }) { Text("Show Done") }
            Button(onClick = { vm.filterByDone(false) }) { Text("Show Todo") }
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
            items(vm.tasks, key = { it.id }) { task ->
                TaskRow(
                    task = task,
                    onToggle = { vm.toggleDone(task.id) },
                    onRemove = { vm.removeTask(task.id) }
                )
            }
        }
    }
}

@Composable
private fun TaskRow(
    task: com.example.vk_1_kotlin_basics.domain.Task,
    onToggle: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
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

            Text(
                modifier = Modifier.weight(1f),
                text = task.title,
                style = MaterialTheme.typography.bodyLarge
            )

            TextButton(onClick = onRemove) {
                Text("Delete")
            }
        }
    }
}
