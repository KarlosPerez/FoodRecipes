package com.karlosprojects.foodrecipes.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.karlosprojects.foodrecipes.core.utils.Constants.DEFAULT_DIET_TYPE
import com.karlosprojects.foodrecipes.core.utils.Constants.DEFAULT_MEAL_TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStorePreferences @Inject constructor(@ApplicationContext val context: Context) {

    private object MealAndTypeKeys {
        val NAME_KEY = stringPreferencesKey("foody_preferences")
        val MEAL_TYPE_KEY = stringPreferencesKey("mealType")
        val MEAL_TYPE_ID_KEY = intPreferencesKey("mealTypeId")
        val DIET_TYPE_KEY = stringPreferencesKey("dietType")
        val DIET_TYPE_ID_KEY = intPreferencesKey("dietTypeId")
        val BACK_ONLINE_KEY = booleanPreferencesKey("backOnline")
    }

    suspend fun saveMealAndDietType(parameters: DataStoreParameters) {
        context.preferencesDataStore.edit { preferences ->
            with(MealAndTypeKeys) {
                preferences[NAME_KEY] = parameters.name
                preferences[MEAL_TYPE_KEY] = parameters.mealType
                preferences[MEAL_TYPE_ID_KEY] = parameters.mealTypeId
                preferences[DIET_TYPE_KEY] = parameters.dietType
                preferences[DIET_TYPE_ID_KEY] = parameters.dietTypeId
                preferences[BACK_ONLINE_KEY] = parameters.backOnline
            }
        }
    }

    val getMealAndTypePreferences = context.preferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            with(MealAndTypeKeys) {
                DataStoreParameters(
                    name = preferences[NAME_KEY] ?: "1",
                    mealType = preferences[MEAL_TYPE_KEY] ?: DEFAULT_MEAL_TYPE,
                    mealTypeId = preferences[MEAL_TYPE_ID_KEY] ?: 0,
                    dietType = preferences[DIET_TYPE_KEY] ?: DEFAULT_DIET_TYPE,
                    dietTypeId = preferences[DIET_TYPE_ID_KEY] ?: 0,
                    backOnline = preferences[BACK_ONLINE_KEY] ?: false,
                )
            }
        }

    companion object {
        private const val MEAL_AND_TYPE_PREFERENCES: String = "meal_and_type_preferences"

        private val Context.preferencesDataStore: DataStore<Preferences>
                by preferencesDataStore(name = MEAL_AND_TYPE_PREFERENCES)
    }
}