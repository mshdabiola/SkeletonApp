package com.mshdabiola.worker

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import com.mshdabiola.worker.work.SyncWorker

object Updater {
    fun initialize(context: Context){
    AppInitializer.getInstance(context)
        .initializeComponent(UpdateInitializer::class.java)
    }
}

 class UpdateInitializer : Initializer<Updater>{
     override fun create(context: Context): Updater {
         WorkManager.getInstance(context)
             .apply {
                 enqueueUniqueWork("updater",
                     ExistingWorkPolicy.KEEP,
                     SyncWorker.startUpSyncWork())
             }
         return Updater
     }

     override fun dependencies()=
         listOf(WorkManagerInitializer::class.java)

 }