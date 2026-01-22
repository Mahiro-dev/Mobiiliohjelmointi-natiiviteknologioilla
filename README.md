# Week 2 tasks 

## Description
Simple task list application built with Kotlin and Jetpack Compose.

Week 2 edits, state management using ViewModel and reactive UI
updates with Jetpack Compose.

## Data model
The application uses a `Task` data class with the following fields:
- id
- title
- description
- priority
- dueDate
- done

Mock data (5 tasks) is initialized in the ViewModel.

## State management
Task state is managed in `TaskViewModel` using `mutableStateOf`.
The ViewModel acts as the single source of truth for the task list and
contains all logic for modifying state.

Using a ViewModel keeps state separate from UI and allows it to survive
configuration changes, unlike `remember` which is tied to a single
Composable.

## Functionality
in the ViewModel:
- addTask / addTaskFromTitle: add a new task
- toggleDone: toggle task completion
- removeTask: delete a task
- filterByDone: show done or todo tasks
- sortByDueDate: sort tasks by due date

The UI updates automatically when the ViewModel state changes
(recomposition).

## UI
The HomeScreen is implemented using Jetpack Compose and displays:
- a list of tasks using LazyColumn
- checkbox for task completion
- delete button per task
- buttons for sorting and filtering
- TextField and button for adding new tasks

Layout is built using basic Compose components such as Column, Row,
Modifier, and Card. No XML layouts are used.

## How to run
1. Open the project in Android Studio
2. Sync Gradle
3. Run the app on an Android Emulator

An APK is also provided for direct installation.
