package com.example.vk_1_kotlin_basics.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vk_1_kotlin_basics.domain.Task
import com.example.vk_1_kotlin_basics.domain.Priority
import java.time.LocalDate

class TaskViewModel : ViewModel() {

    private var allTasks = listOf<Task>()

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var filterDone: Boolean? = null

    init {
        allTasks = listOf(
            Task(1, "Buy groceries", "Milk, eggs, bread", Priority.MEDIUM, LocalDate.of(2026, 1, 15), false),
            Task(2, "Gym", "Leg day", Priority.LOW, LocalDate.of(2026, 1, 17), true),
            Task(3, "Pay rent", "Bank transfer", Priority.HIGH, LocalDate.of(2026, 1, 16), false),
            Task(4, "Study Kotlin", "ViewModel + state", Priority.HIGH, LocalDate.of(2026, 1, 19), false),
            Task(5, "Car maintenance", "Check oil", Priority.MEDIUM, LocalDate.of(2026, 1, 21), true),
        )
        tasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        refreshVisible()
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
        refreshVisible()
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        refreshVisible()
    }

    fun filterByDone(done: Boolean) {
        filterDone = done
        refreshVisible()
    }

    fun clearFilter() {
        filterDone = null
        refreshVisible()
    }
    private var sortAscending = true

    fun sortByDueDate() {
        allTasks = if (sortAscending) {
            allTasks.sortedBy { it.dueDate }
        } else {
            allTasks.sortedByDescending { it.dueDate }
        }

        sortAscending = !sortAscending
        refreshVisible()
    }


    private fun refreshVisible() {
        tasks = if (filterDone == null) {
            allTasks
        } else {
            allTasks.filter { it.done == filterDone }
        }
    }

    private fun nextId(): Int = (allTasks.maxOfOrNull { it.id } ?: 0) + 1

    fun addTaskFromTitle(title: String) {
        val task = Task(
            id = nextId(),
            title = title,
            description = "",
            priority = Priority.LOW,
            dueDate = LocalDate.now(),
            done = false
        )
        addTask(task)
    }

}

