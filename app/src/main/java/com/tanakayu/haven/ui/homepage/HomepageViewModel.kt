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
            ),
            ProjectDataModel(
                ProjectName.START_ACTIVITY_FOR_RESULT,
                R.drawable.navigation_graph,
                injectedContext.getString(R.string.text_project_title_start_activity_for_result),
                injectedContext.getString(R.string.text_project_story_start_activity_for_result),
            ),
            ProjectDataModel(
                ProjectName.SNACKBAR,
                R.drawable.snackbar,
                injectedContext.getString(R.string.text_mini_project_title_snackbar),
            ),
            ProjectDataModel(
                ProjectName.NDK,
                R.drawable.android_ndk,
                injectedContext.getString(R.string.text_mini_project_title_ndk),
                injectedContext.getString(R.string.text_mini_project_story_ndk),
            ),
            ProjectDataModel(
                ProjectName.JETPACK_COMPOSE,
                R.drawable.jetpack_compose,
                injectedContext.getString(R.string.text_project_title_jetpack_compose),
                injectedContext.getString(R.string.text_project_story_jetpack_compose),
            ),
        )
    }
}