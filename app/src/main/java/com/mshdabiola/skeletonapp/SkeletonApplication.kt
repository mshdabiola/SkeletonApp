package com.mshdabiola.skeletonapp

import android.app.Application
import com.mshdabiola.worker.Updater
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SkeletonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Updater.initialize(this)

        if(packageName.contains("debug")) {
            Timber.plant(Timber.DebugTree())
            Timber.e("log on app create")
        }
    }
}
