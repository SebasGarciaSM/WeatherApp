package com.example.weatherapp.ui.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.viewmodels.CityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "city")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val cityViewModel: CityViewModel by viewModels()
    private val firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            getCityStored().collect { cityName ->
                if(!cityName.isNullOrEmpty()){
                    Log.i("tag", "Ciudad guardada $cityName")
                    runOnUiThread {
                        binding.searchView.setQuery(cityName, true)
                    }
                }
            }
        }

        initUI()
    }

    private fun initUI() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchByName(query)
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        cityViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        cityViewModel.isCityFragmentVisible.observe(this, Observer {
            binding.fragmentContainer.isVisible = it
        })

    }

    private fun searchByName(query: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                cityViewModel.getCity(query)
                saveCityName(query)
            }
        } catch (e: Exception) {
            Toast.makeText(this, R.string.errorMessage, Toast.LENGTH_SHORT).show()
            Log.i("Error", e.toString())
        }
    }

    companion object {
        const val CITY_NAME = "city_name"
    }

    private suspend fun saveCityName(value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(CITY_NAME)] = value
        }
    }

    private fun getCityStored(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(CITY_NAME)] ?: ""
        }
    }
}