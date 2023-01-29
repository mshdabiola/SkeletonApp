package com.mshdabiola.database


import com.mshdabiola.database.SkeletonDatabase
import com.mshdabiola.database.dao.ModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun modelDaoProvider(ludoDatabase: SkeletonDatabase): ModelDao {
        return ludoDatabase.getModelDao()
    }
}
