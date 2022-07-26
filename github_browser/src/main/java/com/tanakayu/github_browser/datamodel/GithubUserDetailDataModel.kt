package com.tanakayu.github_browser.datamodel

data class GithubUserDetailDataModel(
    val id: String,
    val dpUrl: String,
    var name: String? = null,
    var bio: String? = null,
    var location: String? = null,
    var email: String? = null,
    var followers: String? = null,
)