package com.tanakayu.mini_projects.ui.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class MiniProjectsJetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(name = "Martin")
        }
    }
    
    @Composable
    fun Greeting(name: String) {
        Text(text = "Bonjour $name")
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsJetpackComposeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}