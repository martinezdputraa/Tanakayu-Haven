package com.tanakayu.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanakayu.core.network.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

abstract class CoreViewModel(
    private val injectedDispatcher: CoroutineDispatcher
): ViewModel() {

    protected fun <RES: Any> requestApiCall(
        endpoint: suspend () -> ApiResult<RES>,
        onError: (ApiResult.Error) -> Unit = handleApiErrorByDefault(),
        onSuccess: (ApiResult.Success<RES>) -> Unit,
    ) {
        viewModelScope.launch(injectedDispatcher) {
            when(val result = endpoint()) {
                is ApiResult.Success -> {
                    onSuccess(result)
                }
                is ApiResult.Error -> {
                    onError(result)
                }
            }
        }
    }

    private fun handleApiErrorByDefault() = { response: ApiResult.Error ->

    }
}