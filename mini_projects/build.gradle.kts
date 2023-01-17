import com.tanakayu.haven.libs.AndroidX
import com.tanakayu.haven.libs.EasyPermissions
import com.tanakayu.haven.libs.ImagePicker

apply {
    from("$rootDir/shared_config.gradle")
}

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    externalNativeBuild {
        cmake {
            path = file("src/cpp/CMakeLists.txt")
        }
    }
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(ImagePicker.imagePicker)
    "implementation"(EasyPermissions.easyPermissions)
}
