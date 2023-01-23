
//buildscript {
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
//    }
//}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    // alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
//    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.manes)
    alias(libs.plugins.little)

}
//./gradlew versionCatalogUpdate
versionCatalogUpdate {
    // sort the catalog by key (default is true)
    sortByKey.set(true)
    // Referenced that are pinned are not automatically updated.
    // They are also not automatically kept however (use keep for that).
    keep {

        // keep versions without any library or plugin reference
        keepUnusedVersions.set(true)
        // keep all libraries that aren't used in the project
        keepUnusedLibraries.set(true)
        // keep all plugins that aren't used in the project
        keepUnusedPlugins.set(true)
    }
}