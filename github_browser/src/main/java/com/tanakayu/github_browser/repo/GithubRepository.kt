package com.tanakayu.github_browser.repo

import com.tanakayu.core.network.ApiResult
import com.tanakayu.github_browser.api.GithubApiService
import com.tanakayu.github_browser.datamodel.response.GithubSearchUserResponse
import com.tanakayu.github_browser.datamodel.response.GithubUserDetailResponse
import com.tanakayu.github_browser.datamodel.response.GithubUserRepositoriesResponse
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val apiService: GithubApiService,
) {
    suspend fun getUsers(query: String): ApiResult<GithubSearchUserResponse> {
        return ApiResult.Success(apiService.getSearchUsers(query))
    }

    suspend fun getUserDetail(userId: String): ApiResult<GithubUserDetailResponse> {
        return ApiResult.Success(apiService.getUserDetails(userId))
    }

    suspend fun getUserRepositories(userId: String): ApiResult<List<GithubUserRepositoriesResponse>> {
        return ApiResult.Success(apiService.getUserRepositories(userId))
    }
}