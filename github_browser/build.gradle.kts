import com.tanakayu.haven.libs.Jodatime

apply {
    from("$rootDir/shared_config.gradle")
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(Jodatime.jodatime)
}