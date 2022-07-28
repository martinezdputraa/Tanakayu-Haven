package com.tanakayu.mini_projects.ui.ndk

import android.app.Activity
import android.content.Intent
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.R as appcompatR
import com.tanakayu.core.constants.SnackbarType
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.constants.NetworkNativeModuleKeys
import com.tanakayu.mini_projects.databinding.MiniProjectsActivityNdkBinding
import com.tanakayu.mini_projects.di.NdkModule
import com.tanakayu.mini_projects.ui.core.MiniProjectsCoreActivity
import dagger.hilt.android.AndroidEntryPoint
import android.R as AndroidR

@AndroidEntryPoint
class MiniProjectsNdkActivity :
    MiniProjectsCoreActivity<MiniProjectsNdkViewModel, MiniProjectsActivityNdkBinding>() {

    override fun createViewModel() = viewModels<MiniProjectsNdkViewModel>()

    override fun getLayoutId() = R.layout.mini_projects_activity_ndk

    override fun onInitView() {
        viewBinder.buttonAction.setOnClickListener {
            showSnackbar(
                NdkModule.getValueFromJNI(NetworkNativeModuleKeys.valueOf(viewBinder.spinnerSelector.selectedItem.toString())),
                type = SnackbarType.Info
            )
        }
        viewBinder.spinnerSelector.adapter = ArrayAdapter(
            this,
            appcompatR.layout.support_simple_spinner_dropdown_item,
            viewModel.getItems()
        ).apply {
            setDropDownViewResource(AndroidR.layout.simple_spinner_dropdown_item)
        }
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsNdkActivity::class.java)
            activity.startActivity(intent)
        }
    }
}