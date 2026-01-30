package com.example.vk_1_kotlin_basics.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vk_1_kotlin_basics.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Int) -> Unit
) {
    var title by remember(task.id) { mutableStateOf(task.title) }
    var description by remember(task.id) { mutableStateOf(task.description) }
    var done by remember(task.id) { mutableStateOf(task.done) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Task details") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Checkbox(checked = done, onCheckedChange = { done = it })
                    Spacer(Modifier.width(8.dp))
                    Text("Done")
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(
                    task.copy(
                        title = title.trim(),
                        description = description,
                        done = done
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = { onDelete(task.id) }) { Text("Delete") }
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        }
    )
}
