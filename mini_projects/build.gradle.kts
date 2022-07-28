import com.tanakayu.haven.libs.EasyPermissions
import com.tanakayu.haven.libs.ImagePicker

apply {
    from("$rootDir/shared_config.gradle")
}

plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    externalNativeBuild {
        cmake {
            path = file("src/cpp/CMakeLists.txt")
            version = "3.10.2"
        }
    }
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(ImagePicker.imagePicker)
    "implementation"(EasyPermissions.easyPermissions)
}