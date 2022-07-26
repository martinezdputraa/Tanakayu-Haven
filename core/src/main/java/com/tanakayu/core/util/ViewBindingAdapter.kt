package com.tanakayu.core.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tanakayu.core.picasso.CircleTransform

@BindingAdapter("textHideIfEmpty")
fun setTextHideIfEmpty(view: TextView, text: String?) {
    view.text = text
    hideIfTextEmpty(view, text)
}

@BindingAdapter("hideIfTextEmpty")
fun hideIfTextEmpty(view: View, text: String?) = hideViewIf(view, text.isNullOrEmpty())

@BindingAdapter("showIfTextEmpty")
fun showIfTextEmpty(view: View, text: String?) = showViewIf(view, text.isNullOrEmpty())

@BindingAdapter("hideIfListEmpty")
fun hideIfListEmpty(view: View, list: List<*>?) = hideViewIf(view, list.isNullOrEmpty())

@BindingAdapter("showIfListEmpty")
fun showIfListEmpty(view: View, list: List<*>?) = showViewIf(view, list.isNullOrEmpty())

@BindingAdapter("showIf")
fun showViewIf(view: View, condition: Boolean) {
    hideViewIf(view, !condition)
}

@BindingAdapter("hideIf")
fun hideViewIf(view: View, condition: Boolean) {
    if(condition) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if(!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .transform(CircleTransform())
            .into(view)
    }
    hideViewIf(view, url.isNullOrEmpty())
}

@BindingAdapter("imageRes")
fun setImageDrawableResource(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}