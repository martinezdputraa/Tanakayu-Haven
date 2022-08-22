package com.tanakayu.mini_projects.ui.philipp.meditation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.tanakayu.mini_projects.resource.MeditationUIYouTubeTheme
import com.tanakayu.mini_projects.ui.philipp.meditation.ui.HomeScreen

class MiniProjectsMeditationHomeActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUIYouTubeTheme {
                HomeScreen()
            }
        }
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsMeditationHomeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}