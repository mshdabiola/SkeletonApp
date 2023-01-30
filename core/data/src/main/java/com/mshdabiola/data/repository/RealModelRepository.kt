package com.mshdabiola.data.repository

import com.mshdabiola.database.dao.ModelDao
import com.mshdabiola.database.model.asModel
import com.mshdabiola.database.model.asModelEntity
import com.mshdabiola.model.Model
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RealModelRepository @Inject constructor(
    private val modelDao: ModelDao,
) : ModelRepository {
    override suspend fun insertModel(model: Model) {
        modelDao.upsert(model.asModelEntity())
    }

    override fun getModels(): Flow<List<Model>> {
        return modelDao.getModel().map { modelEntities -> modelEntities.map { it.asModel() } }
    }
}
