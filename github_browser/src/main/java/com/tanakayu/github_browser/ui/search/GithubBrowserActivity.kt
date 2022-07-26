package com.tanakayu.github_browser.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.tanakayu.core.R.*
import com.tanakayu.core.ui.CoreActivity
import com.tanakayu.core.util.afterTextChanged
import com.tanakayu.github_browser.R
import com.tanakayu.github_browser.databinding.GithubSearchUserActivityBinding
import com.tanakayu.github_browser.datamodel.GithubUserDetailDataModel
import com.tanakayu.github_browser.ui.core.GithubCoreActivity
import com.tanakayu.github_browser.ui.detail.GithubDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GithubBrowserActivity: GithubCoreActivity<GithubBrowserViewModel, GithubSearchUserActivityBinding>(),
    GithubSearchResultAdapter.OnUserSelectedListener {

    private lateinit var adapter: GithubSearchResultAdapter

    override fun getLayoutId() = R.layout.github_search_user_activity

    override fun createViewModel() = viewModels<GithubBrowserViewModel>()

    override fun onInitView() {
        initViewComponents()
        initLiveData()
    }

    override fun onUserSelected(selectedUserDataModel: GithubUserDetailDataModel) {
        navigateToDetailActivity(selectedUserDataModel.id)
    }

    private val mHandler = Handler()
    private val afterTextChangedTask = Runnable {
        viewModel.fetchUsers()
    }

    private fun initViewComponents() {
        viewBinder.viewModel = viewModel
        viewBinder.editTextInput.afterTextChanged {
            mHandler.removeCallbacks(afterTextChangedTask)
            mHandler.postDelayed(afterTextChangedTask, 500)
        }

        adapter = GithubSearchResultAdapter(this)
        viewBinder.recyclerViewUsers.adapter = adapter
    }

    private fun initLiveData() {
        viewModel.users.observe(this) {
            adapter.setData(it)
        }
    }

    private fun navigateToDetailActivity(userId: String) {
        GithubDetailActivity.startThisActivity(this, userId)
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, GithubBrowserActivity::class.java)
            activity.startActivity(intent)
        }
    }
}