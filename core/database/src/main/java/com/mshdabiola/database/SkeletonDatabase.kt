package com.mshdabiola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mshdabiola.database.dao.ModelDao
import com.mshdabiola.database.model.ModelEntity

@Database(
    entities = [ModelEntity::class],
    version = 1
 )
abstract class SkeletonDatabase : RoomDatabase() {

    abstract fun getModelDao(): ModelDao
//
//    abstract fun getPlayerDao(): PlayerDao
//
//    abstract fun getPawnDao(): PawnDao
}
