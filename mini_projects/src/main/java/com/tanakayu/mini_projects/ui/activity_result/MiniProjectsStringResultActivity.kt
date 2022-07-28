package com.tanakayu.mini_projects.ui.activity_result

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.databinding.MiniProjectsActivityStringResultBinding
import com.tanakayu.mini_projects.ui.core.MiniProjectsCoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MiniProjectsStringResultActivity: MiniProjectsCoreActivity<MiniProjectsStringResultViewModel, MiniProjectsActivityStringResultBinding>() {
    override fun createViewModel() = viewModels<MiniProjectsStringResultViewModel>()

    override fun getLayoutId() = R.layout.mini_projects_activity_string_result

    override fun onInitView() {
        viewBinder.viewModel = viewModel
        viewBinder.buttonFinish.setOnClickListener {
            if(!viewModel.inputText.isNullOrEmpty()) {
                closeWithResult()
            } else {
                showSnackbar(getString(R.string.minirepo_text_cannot_be_empty))
            }
        }
    }

    private fun closeWithResult() {
        intent.putExtra(RESULT_KEY, viewModel.inputText)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val RESULT_KEY = "result_key"

        fun startThisActivity(
            activity: Activity,
            result: ActivityResultLauncher<Intent>? = null
        ) {
            val intent = Intent(activity, MiniProjectsStringResultActivity::class.java)
            if(result != null) {
                result.launch(intent)
            } else {
                activity.startActivity(intent)
            }
        }
    }
}