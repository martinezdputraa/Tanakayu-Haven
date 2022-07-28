package com.tanakayu.mini_projects.ui.browse_image

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.tanakayu.core.constants.SnackbarType
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.databinding.MiniProjectsActivityBrowseImageBinding
import com.tanakayu.mini_projects.ui.activity_result.MiniProjectsStringResultActivity
import com.tanakayu.mini_projects.ui.core.MiniProjectsCoreActivity
import com.tanakayu.mini_projects.ui.activity_result.MiniProjectsStringResultActivity.Companion.RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MiniProjectsBrowseImageActivity:
    MiniProjectsCoreActivity<MiniProjectsBrowseImageViewModel, MiniProjectsActivityBrowseImageBinding>() {

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringExtra(RESULT_KEY)?.also {
                showSnackbar(it, type = SnackbarType.Success)
            }
        }
    }

    override fun createViewModel() = viewModels<MiniProjectsBrowseImageViewModel>()

    override fun getLayoutId() = R.layout.mini_projects_activity_browse_image

    override fun onInitView() {
        viewBinder.buttonPickImage.setOnClickListener {
            showBottomSheetUploadImage()
        }
        viewBinder.buttonActivityResult.setOnClickListener {
            goToActivityResult()
        }
    }

    override fun onFileFetchedCallback(file: File, uri: Uri) {
        viewBinder.imageView.setImageURI(uri)
    }

    private fun goToActivityResult() {
        MiniProjectsStringResultActivity.startThisActivity(this, resultLauncher)
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsBrowseImageActivity::class.java)
            activity.startActivity(intent)
        }
    }
}