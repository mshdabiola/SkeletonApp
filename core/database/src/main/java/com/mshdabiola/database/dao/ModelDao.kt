package com.mshdabiola.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.mshdabiola.database.model.ModelEntity

@Dao
interface ModelDao {

    @Upsert
    fun upsert(modelEntity: ModelEntity)
}