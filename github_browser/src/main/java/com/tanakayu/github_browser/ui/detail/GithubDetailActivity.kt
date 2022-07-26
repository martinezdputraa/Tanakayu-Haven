package com.tanakayu.github_browser.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.tanakayu.core.util.gone
import com.tanakayu.core.util.visible
import com.tanakayu.core.util.visibleIf
import com.tanakayu.github_browser.R
import com.tanakayu.github_browser.databinding.GithubActivityUserDetailBinding
import com.tanakayu.github_browser.ui.core.GithubCoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubDetailActivity: GithubCoreActivity<GithubDetailViewModel, GithubActivityUserDetailBinding>() {

    private val adapter = GithubUserRepositoriesAdapter()

    override fun createViewModel() = viewModels<GithubDetailViewModel>()

    override fun getLayoutId() = R.layout.github_activity_user_detail

    override fun onInitView() {
        val userId = intent.getStringExtra(EXTRA_KEY)!!
        viewModel.prepareData(userId)

        startShimmers()
        initViewComponents()
    }

    private fun startShimmers() {
        with(viewBinder.shimmerHeader) {
            if (!isShimmerStarted) startShimmer()
            visible()
            viewBinder.layoutHeader.gone()
        }

        with(viewBinder.shimmerRepositories) {
            if (!isShimmerStarted) startShimmer()
            visible()
            viewBinder.recyclerViewRepositories.gone()
        }
    }

    private fun initViewComponents() {
        viewModel.userDetail.observe(this) {
            viewBinder.apply {
                userData = it
                shimmerHeader.gone()
                layoutHeader.visible()
            }
        }

        viewModel.repositories.observe(this) {
            viewBinder.apply {
                recyclerViewRepositories.visibleIf(!it.isNullOrEmpty())
                textViewNoRepositories.visibleIf(it.isNullOrEmpty())
                separator.visibleIf(!it.isNullOrEmpty())
                shimmerRepositories.gone()
            }
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