package com.tanakayu.haven

import com.tanakayu.haven.libs.AndroidX
import com.tanakayu.haven.libs.Google

object Build {
    private const val gradleVersion = "7.2.2"
    const val gradle = "com.android.tools.build:gradle:$gradleVersion"

    private const val gradlePluginVersion = "1.7.0"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$gradlePluginVersion"

    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Google.hiltVersion}"

    private const val kotlinSerializationVersion = "1.7.0"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${kotlinSerializationVersion}"

    private const val googleServicesVersion = "4.3.10"
    const val googleServices = "com.google.gms:google-services:$googleServicesVersion"

    private const val crashlyticsGradleVersion = "2.7.1"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"

    private const val appDistributionGradleVersion = "2.1.3"
    const val firebaseAppDistribution = "com.google.firebase:firebase-appdistribution-gradle:$appDistributionGradleVersion"

    private const val firebasePerformancePluginVersion = "1.4.0"
    const val firebasePerformance = "com.google.firebase:perf-plugin:$firebasePerformancePluginVersion"

    const val jUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}