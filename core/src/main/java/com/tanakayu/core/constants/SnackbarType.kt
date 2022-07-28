package com.tanakayu.core.constants

import com.tanakayu.core.R

sealed class SnackbarType(val textColor: Int, val backgroundColor: Int) {
    object Error: SnackbarType(R.color.color_red, R.color.color_whitish_pink)
    object Warning: SnackbarType(R.color.color_dark_primary, R.color.color_whitish_yellow)
    object Success: SnackbarType(R.color.color_dark_primary, R.color.color_whitish_green)
    object Info: SnackbarType(R.color.color_dark_primary, R.color.color_whitish_blue)
}
