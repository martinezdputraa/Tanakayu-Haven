package com.tanakayu.mini_projects.resource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.tanakayu.mini_projects.R

object FontBaloo {
    fun get() = FontFamily(
        Font(R.font.baloo2_regular, FontWeight.Normal),
        Font(R.font.baloo2_medium, FontWeight.Medium),
        Font(R.font.baloo2_semibold, FontWeight.SemiBold),
        Font(R.font.baloo2_bold, FontWeight.Bold),
        Font(R.font.baloo2_extrabold, FontWeight.ExtraBold),
    )
}