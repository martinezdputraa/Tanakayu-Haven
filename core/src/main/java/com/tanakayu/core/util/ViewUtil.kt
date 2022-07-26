package com.tanakayu.core.util

import android.view.View


fun View.visible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility =
        View.VISIBLE
}

fun View.gone() {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible() {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}

fun visibleMultipleViews(vararg views: View) {
    views.forEach {
        it.visible()
    }
}

fun goneMultipleViews(vararg views: View) {
    views.forEach {
        it.gone()
    }
}

fun invisibleMultipleViews(vararg views: View) {
    views.forEach {
        it.invisible()
    }
}

fun View.visibleIf(condition: Boolean) {
    if (condition)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}