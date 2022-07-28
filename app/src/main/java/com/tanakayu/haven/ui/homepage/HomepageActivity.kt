package com.tanakayu.haven.ui.homepage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tanakayu.core.constants.SnackbarType
import com.tanakayu.core.ui.CoreActivity
import com.tanakayu.github_browser.ui.search.GithubBrowserActivity
import com.tanakayu.haven.R
import com.tanakayu.haven.databinding.ActivityHomepageBinding
import com.tanakayu.haven.datamodel.ProjectDataModel
import com.tanakayu.haven.datamodel.constants.ProjectName
import com.tanakayu.mini_projects.ui.browse_image.MiniProjectsBrowseImageActivity
import com.tanakayu.mini_projects.ui.snackbars.MiniProjectsSnackbarsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageActivity : CoreActivity<HomepageViewModel, ActivityHomepageBinding>(),
    HomepageAdapter.OnItemSelectedListener {

    private val adapter = HomepageAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.shouldDisplaySplash.value
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun createViewModel() = viewModels<HomepageViewModel>()

    override fun getLayoutId() = R.layout.activity_homepage

    override fun onInitView() {
        viewBinder.rv.adapter = adapter
        adapter.setData(viewModel.generateProjects())
    }

    override fun onItemSelected(item: ProjectDataModel) {
        when (item.projectId) {
            ProjectName.GITHUB_BROWSER -> navigateToGithubBrowser()
            ProjectName.START_ACTIVITY_FOR_RESULT -> navigateToStartActivityForResult()
            ProjectName.SNACKBAR -> navigateToSnackbarsActivity()
            else -> showSnackbar(
                item.projectTitle + " is not yet ready",
                type = SnackbarType.Warning
            )
        }
    }

    private fun navigateToSnackbarsActivity() {
        MiniProjectsSnackbarsActivity.startThisActivity(this)
    }

    private fun navigateToGithubBrowser() {
        GithubBrowserActivity.startThisActivity(this)
    }

    private fun navigateToStartActivityForResult() {
        MiniProjectsBrowseImageActivity.startThisActivity(this)
    }
}