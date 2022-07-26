package com.tanakayu.github_browser.datamodel.response

import com.google.gson.annotations.SerializedName

data class GithubUserRepositoriesResponse (
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("updated_at") val lastUpdated: String,
    @SerializedName("owner") val owner: GithubUserDetailResponse
)