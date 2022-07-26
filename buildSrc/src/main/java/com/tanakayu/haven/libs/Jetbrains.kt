package com.tanakayu.haven.libs

object Jetbrains {
    private const val kotlinVersion = "1.6.0"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    private const val coroutineVersion = "1.3.9"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
}