# Realtime Shopping List Android App

A native Android application built with **Java** and **Firebase**, developed for the **Smart Device Development (SMD)** coursework at FAST-NUCES. The app features secure user authentication, animated splash transitions, and real-time shopping list management backed by Google Firebase Realtime Database and FirebaseUI.

---

## Features

- **Animated Splash Screen**: Custom entry animation with rotating branding transitions (`R.anim.logo_animation`) and automatic intent routing.
- **Firebase Authentication**:
  - Email and password user registration with client-side password length and match verification.
  - User login session handling with Firebase Auth.
  - Password recovery flow with Firebase password reset email dispatch.
  - Secure session logout.
- **Realtime Shopping List (CRUD)**:
  - Add items with name, quantity, and unit price.
  - Real-time updates across devices via Firebase Realtime Database and `FirebaseRecyclerAdapter`.
  - Item deletion directly from list items with instant cloud sync.
- **Robust Input Validation**: Safe numeric parsing for quantities and prices with user-friendly Toast notifications.

---

## Tech Stack & Architecture

| Layer | Technology |
| :--- | :--- |
| **Language** | Java (Java 8 compatibility) |
| **Platform** | Android SDK (compileSdk `34`, minSdk `24`, targetSdk `34`) |
| **Build System** | Gradle (Kotlin DSL, `build.gradle.kts`) |
| **Backend & Auth** | Firebase Authentication (`firebase-auth`) |
| **Database** | Firebase Realtime Database (`firebase-database`, `firebase-ui-database`) |
| **UI Components** | Android Material Design, ConstraintLayout, RecyclerView, FloatingActionButton |

---

## Project Structure

```text
smd-assignment4/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/iamjunaidjutt/assignment4/
│   │   │   │   ├── AddItemActivity.java        # Form to add shopping items to Firebase
│   │   │   │   ├── LoginActivity.java          # Firebase email/password sign-in
│   │   │   │   ├── RegisterActivity.java       # User account creation
│   │   │   │   ├── ResetPasswordActivity.java  # Password reset dispatch
│   │   │   │   ├── ShoppingItem.java           # Shopping item data model
│   │   │   │   ├── ShoppingListAdapter.java    # FirebaseRecyclerAdapter for list items
│   │   │   │   ├── ShoppingListActivity.java   # Main dashboard displaying live items
│   │   │   │   └── SplashScreen.java           # Animated splash entry activity
│   │   │   └── res/
│   │   │       ├── anim/                       # Logo rotation animation
│   │   │       ├── layout/                     # Activity & item XML layouts
│   │   │       └── values/                     # Colors, themes, and dimensions
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── google-services.json.example            # Sample Firebase config template
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## Getting Started

### Prerequisites

- **Android Studio** (Hedgehog | Iguana | Ladybug or newer)
- **JDK 17** (or standard Android Studio bundled JDK)
- An active **Firebase Project** with Authentication and Realtime Database enabled

### Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/iamjunaidjutt/smd-assignment4.git
   cd smd-assignment4
   ```

2. **Configure Firebase**:
   - Create a project on the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app with package name:
     ```text
     com.iamjunaidjutt.assignment4
     ```
   - Enable **Email/Password** provider under **Build > Authentication > Sign-in method**.
   - Create a **Realtime Database** instance under **Build > Realtime Database**.
   - Download your `google-services.json` file and place it in the `app/` directory:
     ```bash
     cp path/to/your/google-services.json app/google-services.json
     ```
     *(Refer to `app/google-services.json.example` for the expected schema).*

3. **Build and Run**:
   - Open the project in Android Studio.
   - Let Gradle sync dependencies.
   - Run the app on an Android Emulator or physical device (Android 7.0+ / API 24+).
   - Alternatively, build the debug APK via CLI:
     ```bash
     ./gradlew assembleDebug
     ```

---

## License

This project is licensed under the terms included in the [LICENSE](LICENSE) file.
