import com.tanakayu.haven.libs.EasyPermissions
import com.tanakayu.haven.libs.ImagePicker

apply {
    from("$rootDir/shared_config.gradle")
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(ImagePicker.imagePicker)
    "implementation"(EasyPermissions.easyPermissions)
}