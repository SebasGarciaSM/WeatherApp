package com.example.weatherapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.data.local.contracts.ILocalStorageService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreLocalStorageServiceImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ILocalStorageService {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "city")

    private val cityKey = stringPreferencesKey("city_key")

    override suspend fun saveLastSearchedCity(cityName: String) {
        context.dataStore.edit { preferences ->
            preferences[cityKey] = cityName
        }
    }

    override suspend fun getLastSearchedCity(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[cityKey]
        }.firstOrNull()
    }
}