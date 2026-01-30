package com.example.vk_1_kotlin_basics.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vk_1_kotlin_basics.model.Priority
import com.example.vk_1_kotlin_basics.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class TaskViewModel : ViewModel() {

    // Source of truth (all tasks)
    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private var filterDone: Boolean? = null
    private var sortAscending = true

    init {
        val initial = listOf(
            Task(1, "Buy groceries", "Milk, eggs, bread", Priority.MEDIUM, LocalDate.of(2026, 1, 15), false),
            Task(2, "Gym", "Leg day", Priority.LOW, LocalDate.of(2026, 1, 17), true),
            Task(3, "Pay rent", "Bank transfer", Priority.HIGH, LocalDate.of(2026, 1, 16), false),
            Task(4, "Study Kotlin", "ViewModel", Priority.HIGH, LocalDate.of(2026, 1, 19), false),
            Task(5, "Car maintenance", "Check oil", Priority.MEDIUM, LocalDate.of(2026, 1, 21), true),
        )
        _allTasks.value = initial
        refreshVisible()
    }

    fun addTask(task: Task) {
        _allTasks.value = _allTasks.value + task
        refreshVisible()
    }

    fun addTaskFromTitle(title: String) {
        val clean = title.trim()
        if (clean.isEmpty()) return

        val task = Task(
            id = nextId(),
            title = clean,
            description = "",
            priority = Priority.LOW,
            dueDate = LocalDate.now(),
            done = false
        )
        addTask(task)
    }

    fun toggleDone(id: Int) {
        _allTasks.value = _allTasks.value.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
        refreshVisible()
    }

    fun removeTask(id: Int) {
        _allTasks.value = _allTasks.value.filter { it.id != id }
        refreshVisible()
    }

    fun updateTask(updated: Task) {
        _allTasks.value = _allTasks.value.map { t ->
            if (t.id == updated.id) updated else t
        }
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

    fun sortByDueDate() {
        val current = _allTasks.value

        _allTasks.value = if (sortAscending) {
            current.sortedBy { it.dueDate }
        } else {
            current.sortedByDescending { it.dueDate }
        }

        sortAscending = !sortAscending
        refreshVisible()
    }

    private fun refreshVisible() {
        val all = _allTasks.value
        _tasks.value = if (filterDone == null) {
            all
        } else {
            all.filter { it.done == filterDone }
        }
    }

    private fun nextId(): Int = (_allTasks.value.maxOfOrNull { it.id } ?: 0) + 1
}
