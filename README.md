## Navigation & Architecture

### Jetpack Compose navigation
Navigation in Jetpack Compose is handled inside a single Activity using composable destinations instead of multiple Activities or Fragments. Each screen is represented by a Composable function, and navigation between screens is managed by a NavController.

NavController is responsible for handling navigation actions (navigate, back).  
NavHost defines the navigation graph and maps routes to Composable screens.

### App navigation structure
This app uses a simple navigation structure with two main routes:
- `home` -> HomeScreen (task list)
- `calendar` -> CalendarScreen (calendar-style view)

Navigation is implemented in `MainActivity` using `NavHost`.  
Users can move between Home and Calendar using UI buttons, and the system back button works automatically.

---

## MVVM + Shared ViewModel

The app follows the MVVM (Model–View–ViewModel) architecture:
- **Model**: `Task` data class
- **ViewModel**: `TaskViewModel`
- **View**: Compose UI screens

single `TaskViewModel` instance is created at the NavHost level and shared between both HomeScreen and CalendarScreen.
- The ViewModel is not recreated when navigating
- Both screens always show the same data
- Changes made in one screen are immediately visible in the other

UI screens observe the ViewModel state using `collectAsState()`, so the UI updates automatically when data changes.

---

## CalendarScreen implementation
CalendarScreen displays tasks grouped by their `dueDate`. Tasks are grouped using `groupBy(dueDate)` and shown with:
- A date header
- Tasks listed under each date

This creates a clear, calendar-like view without using a complex calendar component.

---

## Dialogs for adding and editing tasks
Task creation and editing are handled using `AlertDialog` components instead of separate navigation screens.

- **Add task**:  
  Triggered by a `+ Add` button. Opens a dialog with input fields for title and description. Saving calls `addTask()` in the ViewModel.

- **Edit task**:  
  Triggered by tapping an existing task. Opens a dialog with pre-filled values. Saving calls `updateTask()`, deleting calls `removeTask()`.

Dialogs allow simple data input without increasing navigation complexity.

## How to run

1. Open the project in Android Studio

2. Sync Gradle

3. Run the app on an Android Emulator or physical Android device

An APK is also provided for direct installation.
