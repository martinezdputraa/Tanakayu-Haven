package com.tanakayu.github_browser.api

import com.tanakayu.github_browser.datamodel.response.GithubSearchUserResponse
import com.tanakayu.github_browser.datamodel.response.GithubUserDetailResponse
import com.tanakayu.github_browser.datamodel.response.GithubUserRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET(urlSearchUsers)
    suspend fun getSearchUsers(@Query("q") query: String): GithubSearchUserResponse

    @GET(urlUser)
    suspend fun getUserDetails(@Path("user_id") userId: String): GithubUserDetailResponse

    @GET(urlUserRepositories)
    suspend fun getUserRepositories(@Path("user_id") userId: String): List<GithubUserRepositoriesResponse>

    companion object {
        private const val urlSearchUsers = "search/users"
        private const val urlUser = "users/{user_id}"
        private const val urlUserRepositories = "users/{user_id}/repos"
    }
}