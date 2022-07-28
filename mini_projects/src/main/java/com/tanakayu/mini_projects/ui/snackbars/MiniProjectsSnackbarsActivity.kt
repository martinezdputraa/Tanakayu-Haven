package com.tanakayu.mini_projects.ui.snackbars

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tanakayu.core.constants.SnackbarType
import com.tanakayu.core.util.takeIfNotNullOrEmpty
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.databinding.MiniProjectsActivitySnackbarsBinding
import com.tanakayu.mini_projects.ui.core.MiniProjectsCoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MiniProjectsSnackbarsActivity:
    MiniProjectsCoreActivity<MiniProjectsSnackbarsViewModel, MiniProjectsActivitySnackbarsBinding>() {

    override fun createViewModel() = viewModels<MiniProjectsSnackbarsViewModel>()

    override fun getLayoutId() = R.layout.mini_projects_activity_snackbars

    override fun onInitView() {
        viewBinder.viewModel = viewModel
        viewBinder.buttonError.setOnClickListener {
            showSnackbar(viewModel.inputText.takeIfNotNullOrEmpty(getString(R.string.minirepo_text_error_message_example)), Snackbar.LENGTH_SHORT)
        }
        viewBinder.buttonWarning.setOnClickListener {
            showSnackbar(viewModel.inputText.takeIfNotNullOrEmpty(getString(R.string.minirepo_text_warning_message_example)), Snackbar.LENGTH_SHORT, SnackbarType.Warning)
        }
        viewBinder.buttonSuccess.setOnClickListener {
            showSnackbar(viewModel.inputText.takeIfNotNullOrEmpty(getString(R.string.minirepo_text_success_message_example)), Snackbar.LENGTH_SHORT, SnackbarType.Success)
        }
        viewBinder.buttonInfo.setOnClickListener {
            showSnackbar(viewModel.inputText.takeIfNotNullOrEmpty(getString(R.string.minirepo_text_info_message_example)), Snackbar.LENGTH_SHORT, SnackbarType.Info)
        }
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsSnackbarsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}