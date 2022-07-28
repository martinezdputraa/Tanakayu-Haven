package com.tanakayu.core.util

fun String?.takeIfNotNullOrEmpty(defaultValue: String): String {
    return this?.let {
        this.ifEmpty { null }
    } ?: defaultValue
}