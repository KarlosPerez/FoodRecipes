package com.karlosprojects.foodrecipes.domain.repository

import com.karlosprojects.foodrecipes.data.local.datastore.DataStoreParameters
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveMealAndTypePreferences(parameters: DataStoreParameters)

    suspend fun getMealAndTypePreferences(): Flow<DataStoreParameters>
}