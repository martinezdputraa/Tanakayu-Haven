package com.tanakayu.mini_projects.ui.ndk

import com.tanakayu.core.dispatcher.IoDispatcher
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.mini_projects.constants.NetworkNativeModuleKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MiniProjectsNdkViewModel @Inject constructor(
    @IoDispatcher injectedDispatcher: CoroutineDispatcher,
) : CoreViewModel(injectedDispatcher) {
    fun getItems() = NetworkNativeModuleKeys.values()
}