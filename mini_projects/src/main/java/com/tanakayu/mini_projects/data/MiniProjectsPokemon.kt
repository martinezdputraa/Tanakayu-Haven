package com.tanakayu.mini_projects.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MiniProjectsPokemon(
    @DrawableRes val drawableRes: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)
