package com.karlosprojects.foodrecipes.data.repository

import com.karlosprojects.foodrecipes.data.local.datastore.DataStoreParameters
import com.karlosprojects.foodrecipes.data.local.datastore.DataStorePreferences
import com.karlosprojects.foodrecipes.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    val preferences: DataStorePreferences
): DataStoreRepository {

    override suspend fun saveMealAndTypePreferences() {
        TODO("Not yet implemented")
    }

    override suspend fun getMealAndTypePreferences(): Flow<DataStoreParameters> {
        TODO("Not yet implemented")
    }


}