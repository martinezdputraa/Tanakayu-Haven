package com.tanakayu.mini_projects.ui.browse_image

import com.tanakayu.core.dispatcher.IoDispatcher
import com.tanakayu.core.ui.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MiniProjectsBrowseImageViewModel @Inject constructor(
    @IoDispatcher injectedDispatcher: CoroutineDispatcher,
) : CoreViewModel(injectedDispatcher) {

}