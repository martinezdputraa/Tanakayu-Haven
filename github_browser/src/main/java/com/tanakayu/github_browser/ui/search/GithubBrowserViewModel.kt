package com.tanakayu.github_browser.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanakayu.core.dispatcher.IoDispatcher
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.github_browser.datamodel.GithubUserDetailDataModel
import com.tanakayu.github_browser.repo.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class GithubBrowserViewModel @Inject constructor(
    private val usersRepository: GithubRepository,
    @IoDispatcher injectedDispatcher: CoroutineDispatcher
): CoreViewModel(injectedDispatcher) {
    var inputAmount: String? = null

    private val _users = MutableLiveData<List<GithubUserDetailDataModel>>()
    val users: LiveData<List<GithubUserDetailDataModel>> = _users

    fun fetchUsers() {
        val input = inputAmount
        if(!input.isNullOrEmpty()) {
            requestApiCall({
                usersRepository.getUsers(input)
            }) { response ->
                _users.postValue(response.data.items.map { item ->
                    GithubUserDetailDataModel(
                        id = item.userId,
                        dpUrl = item.avatarUrl
                    )
                })
            }
        } else {
            _users.postValue(emptyList())
        }
    }
}