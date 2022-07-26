package com.tanakayu.github_browser.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.tanakayu.core.ui.CoreActivity
import com.tanakayu.core.util.visibleIf
import com.tanakayu.github_browser.R
import com.tanakayu.github_browser.databinding.GithubUserDetailActivityBinding
import com.tanakayu.github_browser.ui.core.GithubCoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubDetailActivity: GithubCoreActivity<GithubDetailViewModel, GithubUserDetailActivityBinding>() {

    private val adapter = GithubUserRepositoriesAdapter()

    override fun createViewModel() = viewModels<GithubDetailViewModel>()

    override fun getLayoutId() = R.layout.github_user_detail_activity

    override fun onInitView() {
        val userId = intent.getStringExtra(EXTRA_KEY)!!
        viewModel.prepareData(userId)

        initViewComponents()
    }

    private fun initViewComponents() {
        viewModel.userDetail.observe(this) {
            viewBinder.userData = it
        }

        viewModel.repositories.observe(this) {
            viewBinder.textViewNoRepositories.visibleIf(it.isNullOrEmpty())
            viewBinder.separator.visibleIf(!it.isNullOrEmpty())
            adapter.setData(it)
        }

        viewBinder.recyclerViewRepositories.adapter = adapter
    }

    companion object {
        const val EXTRA_KEY = "detail.activity.extra.key"

        fun startThisActivity(activity: Activity, userId: String) {
            val intent = Intent(activity, GithubDetailActivity::class.java).apply {
                putExtra(EXTRA_KEY, userId)
            }
            activity.startActivity(intent)
        }
    }
}