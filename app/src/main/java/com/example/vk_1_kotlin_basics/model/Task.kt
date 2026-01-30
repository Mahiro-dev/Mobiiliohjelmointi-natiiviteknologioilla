package com.example.vk_1_kotlin_basics.model

import java.time.LocalDate

enum class Priority { LOW, MEDIUM, HIGH }

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: LocalDate,
    val done: Boolean
)
