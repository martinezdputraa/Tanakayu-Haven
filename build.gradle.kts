buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.tanakayu.haven.Build.gradle)
        classpath(com.tanakayu.haven.Build.gradlePlugin)
        classpath(com.tanakayu.haven.Build.hiltGradlePlugin)
        classpath(com.tanakayu.haven.Build.kotlinSerialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}