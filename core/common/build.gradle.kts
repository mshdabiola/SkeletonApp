plugins {
    id("mshdabiola.android.library")
    id ("mshdabiola.android.hilt")
}

android {
    namespace = "com.mshdabiola.common"
}
dependencies{
    testImplementation(project(":core:testing"))
}