## Architecture (MVVM)

project follows the Model–View–ViewModel (MVVM) pattern.

## Model

Task data class represents application data.
It contains only data fields (id, title, description, priority, dueDate, done)
and has no UI or business logic.

## View

UI is implemented using Jetpack Compose in HomeScreen and DetailDialog.

- Displays a list of tasks

- Handles user input (add, toggle, edit, delete)

- Does not manage application state directly

## ViewModel

TaskViewModel contains all application logic and state.
Manages the task list, Exposes state to the UI and Provides functions:

- addTask

- toggleDone

- removeTask

- updateTask

## State management with StateFlow

task list state is stored in the ViewModel using StateFlow. StateFlow always holds the current state When the ViewModel updates the state, a new value is emitted
The UI observes the state using collectAsState()
changes in the ViewModel are immediately reflected in the UI without manual refresh logic.

## Why ViewModel + StateFlow instead of remember

Using ViewModel with StateFlow is good because
State survives configuration changes like screen rotation.
Easier to maintain and extend than remember only state

## UI features

Task list displayed using LazyColumn

- Add new tasks
- Toggle task completion
- Filter tasks (Done / Todo / All)
- Sort tasks by due date
- Edit and delete tasks using a detail dialog

## How to run

1. Open the project in Android Studio

2. Sync Gradle

3. Run the app on an Android Emulator or physical Android device

An APK is also provided for direct installation.
