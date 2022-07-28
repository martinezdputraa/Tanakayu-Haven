package com.tanakayu.core.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.tanakayu.core.R
import com.tanakayu.core.constants.SnackbarType

abstract class CoreActivity<VM: CoreViewModel, VB: ViewDataBinding>: AppCompatActivity() {
    private lateinit var mViewModel: VM

    protected lateinit var viewBinder: VB

    val viewModel: VM
        get() = mViewModel

    abstract fun createViewModel(): Lazy<VM>

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onInitView()

    open fun onActionBarBackButtonPressed() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel().value
        performViewBinding()
        onInitView()

        window.statusBarColor = ContextCompat.getColor(this, R.color.color_blue_sky)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onActionBarBackButtonPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun performViewBinding() {
        viewBinder = DataBindingUtil.setContentView(this, getLayoutId())
        viewBinder.executePendingBindings()
    }

    fun showSnackbar(
        message: String?,
        duration: Int = Snackbar.LENGTH_LONG,
        type: SnackbarType = SnackbarType.Error
    ) {
        val snackbar = Snackbar.make(viewBinder.root, message ?: "", duration)
            .setBackgroundTint(ContextCompat.getColor(this, type.backgroundColor))
            .setTextColor(ContextCompat.getColor(this, type.textColor))
        val snackbarView = snackbar.view
        val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.maxLines = 4
        snackbar.show()
    }
}