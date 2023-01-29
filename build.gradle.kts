buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}
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



//tasks.create("add_spot"){
//    val proj=project.rootDir
//
//  //  doFirst{
//        print("add spotless to git")
//        val gitHooksDirectory = File("$proj/.git/hooks/")
//        if (!gitHooksDirectory.exists()) gitHooksDirectory.mkdirs()
//        File("$proj/.git/hooks", "pre-commit").writeText(
//            """
//    #!/bin/bash
//    echo "Running spotless check"
//    ./gradlew spotlessApply --init-script gradle/init.gradle.kts
//"""
//        )
//
//        exec {
//            commandLine("chmod","+x", ".git/hooks/pre-commit")
//        }
//
//  //  }
//
//}
//.gradlew add_spot