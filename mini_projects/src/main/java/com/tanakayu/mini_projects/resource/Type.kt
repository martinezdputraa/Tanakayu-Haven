package com.tanakayu.mini_projects.resource

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.resource.AquaBlue
import com.tanakayu.mini_projects.resource.TextWhite

val gothicA1 = FontFamily(
    listOf(
        Font(R.font.baloo2_regular, FontWeight.Normal),
        Font(R.font.baloo2_medium, FontWeight.Medium),
        Font(R.font.baloo2_medium, FontWeight.SemiBold),
        Font(R.font.baloo2_bold, FontWeight.Bold),
        Font(R.font.baloo2_extrabold, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = AquaBlue,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        color = TextWhite,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = TextWhite,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)