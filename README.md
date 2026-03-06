# CMP Notes

A **notes and tasks** app built with **Kotlin Multiplatform** and **Compose Multiplatform**. One codebase runs on **Android**, **iOS**, and **Desktop (JVM)**.

---

## Features

- **Notes** — Create notes with title and description
- **Priority** — Urgent, High, Normal, Low
- **Reminder date** — Set a date for each note
- **Search** — Find notes by title
- **Swipe to delete** — Swipe a note card to remove it
- **Onboarding** — Three intro screens (skip or go through)
- **Liquid splash** — Animated splash on first launch
- **Settings** — Dark theme toggle, About
- **Persistent storage** — SQLDelight (local SQLite on each platform)

---

## Tech Stack

| Layer        | Technology                          |
|-------------|--------------------------------------|
| UI          | Compose Multiplatform (Material 3)   |
| Navigation  | Voyager                             |
| Database    | SQLDelight                          |
| Concurrency | Kotlin Coroutines, Flow              |
| Dates       | kotlinx-datetime                     |

**Targets:** Android, iOS (arm64 + simulator), JVM (Desktop)

---

## Screenshots

Add your app screenshots to the `docs/screenshots/` folder, then reference them below (or replace the paths once you have images).

| | |
|---|---|
| **Home** — Notes list, search, FAB | **Add / Edit Note** — Title, priority, reminder, description |
| ![Home](docs/screenshots/home.png) | ![Add Note](docs/screenshots/add-note.png) |
| **Note details** — View and edit | **Onboarding** — Intro screens |
| ![Note details](docs/screenshots/note-details.png) | ![Onboarding](docs/screenshots/onboarding.png) |
| **Settings** — Dark theme, About | **Splash** — Liquid animation |
| ![Settings](docs/screenshots/settings.png) | ![Splash](docs/screenshots/splash.png) |

*To add screenshots:* place image files (e.g. `home.png`, `add-note.png`) in `docs/screenshots/` and ensure the filenames match the paths above.

---

## Project Structure

```
CMP_Project/
├── composeApp/
│   ├── src/
│   │   ├── commonMain/kotlin/org/cmp/project/
│   │   │   ├── App.kt
│   │   │   ├── onboarding/          # First-time onboarding preference
│   │   │   ├── settings/            # Theme (dark/light) preference
│   │   │   ├── data/                # SQLDelight, repository, models
│   │   │   ├── domain/              # Extensions (date format, logging)
│   │   │   └── ui/
│   │   │       ├── screens/         # Home, AddNotes, NoteDetails, Settings, onboarding, splash, gate
│   │   │       ├── theme/           # Colors, typography
│   │   │       └── universalComponents/
│   │   ├── androidMain/            # MainActivity, SQLDelight driver, theme prefs
│   │   ├── iosMain/                # MainViewController, drivers, prefs
│   │   └── jvmMain/                # main(), desktop entry, drivers, prefs
│   └── build.gradle.kts
├── settings.gradle.kts
└── build.gradle.kts
```

---

## Build & Run

### Android

```bash
./gradlew :composeApp:assembleDebug
```

Run from Android Studio or install the APK from `composeApp/build/outputs/apk/debug/`.

### Desktop (JVM)

```bash
./gradlew :composeApp:run
```

### iOS

1. Build the Compose framework (e.g. from Android Studio or CLI).
2. Open the iOS app project in Xcode (if present) or use the generated framework and run from Xcode.

---

## Requirements

- **JDK 11+**
- **Android Studio** (or IntelliJ) with Kotlin and Compose Multiplatform support
- **Xcode** (for iOS)
- **Kotlin** 2.3.x, **Compose Multiplatform** 1.10.x

---

## License

This project is open source. Use it as you like.
