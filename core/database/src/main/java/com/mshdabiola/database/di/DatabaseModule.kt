package com.mshdabiola.database.di

import android.content.Context
import androidx.room.Room
import com.mshdabiola.database.SkeletonDatabase
import com.mshdabiola.database.dao.ModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(
        @ApplicationContext context: Context
    ): SkeletonDatabase {
        return Room.databaseBuilder(context, SkeletonDatabase::class.java, "skeletonDb.db")
            .build()
 //        return Room.inMemoryDatabaseBuilder(context,LudoDatabase::class.java,)
 //            .build()
    }

    @Provides
    @Singleton
    fun modelDaoProvider(ludoDatabase: SkeletonDatabase): ModelDao {
        return ludoDatabase.getModelDao()
    }

}
