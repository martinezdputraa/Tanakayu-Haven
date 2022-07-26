package com.tanakayu.github_browser.datamodel.response

import com.google.gson.annotations.SerializedName

data class GithubSearchUserResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: List<GithubUserDetailResponse>
)


