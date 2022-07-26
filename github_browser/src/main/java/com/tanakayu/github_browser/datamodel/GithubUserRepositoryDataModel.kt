package com.tanakayu.github_browser.datamodel

data class GithubUserRepositoryDataModel(
    val name: String,
    val description: String?,
    val stars: Int,
    val lastUpdated: String,
    val ownerDpUrl: String
)
