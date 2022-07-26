package com.tanakayu.haven.ui.homepage

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.tanakayu.core.dispatcher.IoDispatcher
import com.tanakayu.core.ui.CoreViewModel
import com.tanakayu.haven.R
import com.tanakayu.haven.datamodel.ProjectDataModel
import com.tanakayu.haven.datamodel.constants.ProjectName
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    @IoDispatcher injectedDispatcher: CoroutineDispatcher,
    @ApplicationContext private val injectedContext: Context
) : CoreViewModel(injectedDispatcher) {

    val shouldDisplaySplash = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            delay(1500)
            shouldDisplaySplash.value = false
        }
    }

    fun generateProjects(): List<ProjectDataModel> {
        return listOf(
            ProjectDataModel(
                ProjectName.GITHUB_BROWSER,
                R.drawable.github_borderless,
                injectedContext.getString(R.string.text_project_title_github),
                injectedContext.getString(R.string.text_project_story_github),
            ),
            ProjectDataModel(
                ProjectName.PAYPAY,
                R.drawable.paypay,
                injectedContext.getString(R.string.text_project_title_paypay),
                injectedContext.getString(R.string.text_project_story_paypay),
            ),
            ProjectDataModel(
                ProjectName.WHEEL_SIMULATOR,
                R.drawable.gojek,
                injectedContext.getString(R.string.text_project_title_wheel_simulator),
                injectedContext.getString(R.string.text_project_story_wheel_simulator),
            )
        )
    }
}