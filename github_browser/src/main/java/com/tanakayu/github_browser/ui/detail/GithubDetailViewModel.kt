package com.tanakayu.github_browser.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanakayu.core.dispatcher.IoDispatcher
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.github_browser.R
import com.tanakayu.github_browser.datamodel.GithubUserDetailDataModel
import com.tanakayu.github_browser.datamodel.GithubUserRepositoryDataModel
import com.tanakayu.github_browser.repo.GithubRepository
import com.tanakayu.github_browser.util.GithubDateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class GithubDetailViewModel @Inject constructor(
    private val usersRepository: GithubRepository,
    @IoDispatcher injectedDispatcher: CoroutineDispatcher,
    @ApplicationContext private val injectedContext: Context
    ): CoreViewModel(injectedDispatcher) {

    private val _userDetail = MutableLiveData<GithubUserDetailDataModel>()
    val userDetail: LiveData<GithubUserDetailDataModel> = _userDetail

    private val _repositories = MutableLiveData<List<GithubUserRepositoryDataModel>>()
    val repositories: LiveData<List<GithubUserRepositoryDataModel>> = _repositories

    fun prepareData(userId: String) {
        fetchUserDetails(userId)
        fetchUserRepositories(userId)
    }

    private fun fetchUserDetails(userId: String) {

        requestApiCall({
            usersRepository.getUserDetail(userId)
        }) {
            val response = it.data
            _userDetail.postValue(GithubUserDetailDataModel(response.userId, response.avatarUrl).apply {
                name = response.name
                bio = response.bio
                location = response.location
                email = response.email
                followers = injectedContext.getString(
                    R.string.github_text_followers_x_following_count,
                    response.followersCount,
                    response.followingCount)
            })
        }
    }

    private fun fetchUserRepositories(userId: String) {
        requestApiCall({
            usersRepository.getUserRepositories(userId)
        }) { repositories ->
            val repoDataModel = repositories.data.map { userRepo ->
                val lastUpdateFormatted = GithubDateHelper.getFormattedPastTime(userRepo.lastUpdated, injectedContext)
                GithubUserRepositoryDataModel(
                    userRepo.name,
                    userRepo.description,
                    userRepo.stars,
                    injectedContext.getString(R.string.github_text_updated_x_ago, lastUpdateFormatted),
                    userRepo.owner.avatarUrl
                )
            }
            _repositories.postValue(repoDataModel)
        }
    }
}