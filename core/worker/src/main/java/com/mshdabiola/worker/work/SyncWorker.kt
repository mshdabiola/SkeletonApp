package com.mshdabiola.worker.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.mshdabiola.common.appdispatchers.AppDispatchers
import com.mshdabiola.common.appdispatchers.Dispatcher
import com.mshdabiola.data.repository.ModelRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val modelRepository: ModelRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun getForegroundInfo(): ForegroundInfo =
        appContext.syncForegroundInfo()


    override suspend fun doWork(): Result = withContext(ioDispatcher) {

        repeat(10){
            Timber.tag("worker").e("number %s", it)
            delay(1000)
        }
        Result.success()
    }


    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(UpdaterConstraints)
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}


private const val NotificationId = 0
private const val NotificationChannelID = "NotificationChannel"

val UpdaterConstraints
    get() = Constraints.Builder()
        .build()

fun Context.syncForegroundInfo() = ForegroundInfo(
    NotificationId,
    syncWorkNotification(),
)

/**
 * Notification displayed on lower API levels when sync workers are being
 * run with a foreground service
 */
private fun Context.syncWorkNotification(): Notification {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            NotificationChannelID,
            "updater",
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = "for updating data"
        }
        // Register the channel with the system
        val notificationManager: NotificationManager? =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.createNotificationChannel(channel)
    }

    return NotificationCompat.Builder(
        this,
        NotificationChannelID,
    )
        .setSmallIcon(
            android.R.drawable.sym_def_app_icon,
        )
        .setContentTitle("Updater")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
}
