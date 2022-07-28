package com.tanakayu.mini_projects.di

import com.tanakayu.mini_projects.constants.NetworkNativeModuleKeys
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NdkModule {
    init {
        System.loadLibrary("native-lib")
    }

    private external fun adrNativeValues(): Map<String, String>

    fun getValueFromJNI(key: NetworkNativeModuleKeys): String {
        return adrNativeValues()[key.value]
            ?: throw IllegalStateException("Key was not found, did you forget to add/remove it in native-lib.cpp?")
    }
}