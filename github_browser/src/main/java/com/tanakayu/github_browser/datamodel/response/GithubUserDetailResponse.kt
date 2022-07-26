package com.tanakayu.github_browser.datamodel.response

import com.google.gson.annotations.SerializedName

data class GithubUserDetailResponse(
    @SerializedName("login") val userId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("bio") val bio: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("following") val followingCount: Int
)
