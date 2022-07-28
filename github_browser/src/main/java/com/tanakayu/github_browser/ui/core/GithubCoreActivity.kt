package com.tanakayu.github_browser.ui.core

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.tanakayu.core.R
import com.tanakayu.core.ui.CoreActivity
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.github_browser.R.*

abstract class GithubCoreActivity<VM: CoreViewModel, VB: ViewDataBinding> : CoreActivity<VM, VB>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val darkColor = ContextCompat.getColor(this, R.color.color_dark_primary)
        window.statusBarColor = darkColor
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(darkColor))
            title = resources.getString(string.github_text_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActionBarBackButtonPressed() {
        finish()
    }
}