package com.tanakayu.core.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tanakayu.core.R

abstract class CoreActivity<VM: CoreViewModel, VB: ViewDataBinding>: AppCompatActivity() {
    private lateinit var mViewModel: VM

    protected lateinit var viewBinder: VB

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

    val viewModel: VM
        get() = mViewModel
}