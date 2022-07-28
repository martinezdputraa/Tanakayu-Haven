package com.tanakayu.github_browser.util

import android.content.Context
import com.tanakayu.core.R
import org.joda.time.DateTime

object GithubDateHelper {
    fun getFormattedPastTime(iso8601: String, context: Context): String {
        val currentTimeMillis = System.currentTimeMillis()
        val lastUpdatedTimeMillis = DateTime(iso8601).millis
        val result = currentTimeMillis - lastUpdatedTimeMillis
        val days = result / (1000 * 60 * 60) / 24
        val months = days / 30
        val years = months / 12

        return when {
            years > 0 -> context.resources.getQuantityString(R.plurals.year, years.toInt(), years.toInt())
            months > 0 -> context.resources.getQuantityString(R.plurals.month, months.toInt(), months.toInt())
            days > 0 -> context.resources.getQuantityString(R.plurals.day, days.toInt(), days.toInt())
            else -> {
                val hours = result / (1000 * 60 * 60)
                context.resources.getQuantityString(R.plurals.hour, hours.toInt(), hours.toInt())
            }
        }
    }
}