package com.tanakayu.haven.datamodel

import com.tanakayu.haven.datamodel.constants.ProjectName

data class ProjectDataModel(
    val projectId: ProjectName,
    val iconResId: Int,
    val projectTitle: String,
    val projectStory: String? = null
)
