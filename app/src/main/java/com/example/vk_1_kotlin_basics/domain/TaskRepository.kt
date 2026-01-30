package com.example.vk_1_kotlin_basics.domain

import com.example.vk_1_kotlin_basics.model.Priority
import com.example.vk_1_kotlin_basics.model.Task
import java.time.LocalDate

object TaskRepository {
    val mockTasks: List<Task> = listOf(
        Task(
            1,
            "Buy groceries",
            "Milk, eggs, rice",
            Priority.MEDIUM,
            LocalDate.now().plusDays(1),
            false
        ),
        Task(2, "Gym", "Leg day", Priority.LOW, LocalDate.now().plusDays(3), true),
        Task(
            3,
            "Pay rent",
            "Transfer before due date",
            Priority.HIGH,
            LocalDate.now().plusDays(2),
            false
        ),
        Task(
            4,
            "Study Kotlin",
            "Practice data classes + functions",
            Priority.HIGH,
            LocalDate.now().plusDays(5),
            false
        ),
        Task(
            5,
            "Car maintenance",
            "Check oil level and tire pressure",
            Priority.MEDIUM,
            LocalDate.now().plusDays(7),
            true
        ),
        Task(6, "Clean desk", "10 minutes only", Priority.LOW, LocalDate.now().plusDays(4), false)
    )
}
