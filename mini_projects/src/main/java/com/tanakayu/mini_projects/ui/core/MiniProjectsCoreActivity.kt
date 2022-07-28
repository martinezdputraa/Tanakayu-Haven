package com.tanakayu.mini_projects.ui.core

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tanakayu.core.R
import com.tanakayu.core.ui.CoreActivity
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.mini_projects.R.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

abstract class MiniProjectsCoreActivity<VM: CoreViewModel, VB: ViewDataBinding> : CoreActivity<VM, VB>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = ContextCompat.getColor(this, R.color.color_pink)
        window.statusBarColor = color
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(color))
            title = resources.getString(string.minirepo_text_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActionBarBackButtonPressed() {
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    protected open fun onFileFetchedCallback(file: File?) {

    }

    @AfterPermissionGranted(DEFAULT_PERMISSION_REQUEST_CODE)
    protected fun showBottomSheetUploadImage() {
        if (hasReadExternalStoragePermission(this)) {
            val bottomSheetDialog = BottomSheetDialog(this).apply {
                setContentView(layout.mini_projects_bottom_sheet_dialog_pick_media)
            }
            val camera = bottomSheetDialog.findViewById<AppCompatButton>(id.button_camera)
            val gallery = bottomSheetDialog.findViewById<AppCompatButton>(id.button_gallery)

            bottomSheetDialog.show()

            camera?.setOnClickListener {
                ImagePicker.with(this)
                    .cameraOnly()
                    .createIntent { intent -> startForImageResult.launch(intent) }
                bottomSheetDialog.dismiss()
            }

            gallery?.setOnClickListener {
                startForGalleryResult.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                )
                bottomSheetDialog.dismiss()
            }
        } else requestReadExternalStoragePermission(this, DEFAULT_PERMISSION_REQUEST_CODE)
    }

    /** ACTIVITY RESULT LAUNCHERS **/
    private val startForImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.path?.also { filePath ->
                val file = File(filePath)
                if (file.exists()) {
                    onFileFetchedCallback(file)
                } else {
                    showSnackbar(getString(string.minirepo_text_unable_to_access))
                }
            }
        }
    }

    private val startForGalleryResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.path?.also { filePath ->
                val file = File(Environment.getExternalStorageDirectory().path + "/" + filePath)
                if (file.exists()) {
                    onFileFetchedCallback(file)
                } else {
                    showSnackbar(getString(string.minirepo_text_unable_to_access))
                }
            }
        }
    }

    /** PRIVATE FUNS **/
    @TargetApi(Build.VERSION_CODES.M)
    private fun hasReadExternalStoragePermission(context: Context): Boolean {
        return EasyPermissions.hasPermissions(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
        )
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestReadExternalStoragePermission(host: Activity, requestCode: Int) {
        EasyPermissions.requestPermissions(
            host,
            getString(string.minirepo_text_request_camera_storage_description),
            requestCode,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    companion object {
        private const val DEFAULT_PERMISSION_REQUEST_CODE = 100
    }
}