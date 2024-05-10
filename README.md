# Note App

## Tools
- Kotlin
- Room
- ViewModel
- Dagger Hilt

## Features
- Add Note
- Update Note
- Delete Note
- View Note
- View All Notes
- Delete All Notes

## Procedures of creating
1. Create a new project in Android Studio
   2. Add dependencies in build.gradle
       1. Start with adding room dependancies on the App level build.gradle.kts following the procedures on the official documentation [here](https://developer.android.com/jetpack/androidx/releases/room)
       ```kotlin
      dependencies {
           val room_version = "2.6.1"

           implementation("androidx.room:room-runtime:$room_version")
           annotationProcessor("androidx.room:room-compiler:$room_version")

           // To use Kotlin annotation processing tool (kapt)
           kapt("androidx.room:room-compiler:$room_version")
           // To use Kotlin Symbol Processing (KSP)
           ksp("androidx.room:room-compiler:$room_version")

           // optional - Kotlin Extensions and Coroutines support for Room
           implementation("androidx.room:room-ktx:$room_version")
       }
       ```
      Adding final room extension that requires the use of couroutines to avoid some errors
    
       2. Add Dagger Hilt dependencies
         + [official documentation](https://developer.android.com/training/dependency-injection/hilt-android)
         start with
         ```kotlin
            plugins {
              id("com.google.dagger.hilt.android") version "2.44" apply false
            }
      ```
      The above goes to the product level build.gradle.kts file
      + Then add the following to the app level build.gradle.kts file

```Kotlin
   plugins {
      id 'kotlin-kapt'
      id("com.google.dagger.hilt.android")
   }
   dependencies {
      implementation("com.google.dagger:hilt-android:2.44")
      kapt("com.google.dagger:hilt-android-compiler:2.44")
   }
   // Allow references to generated code
   kapt {
      correctErrorTypes = true
   }
```

4. Sync the project

Incase of any error please make sure you have the latest version of the dependencies and you clean the project