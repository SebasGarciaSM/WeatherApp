package com.example.weatherapp.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.screens.CityLookupScreen
import com.example.weatherapp.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "city")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val cityViewModel: CityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherTheme {
                Scaffold(
                    modifier = Modifier,
                    topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }) { safePadding ->
                    CityLookupScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(safePadding)
                    )
                }
            }
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        //This coroutine is executed when there's data saved in DataStore
        //and then searches for the result
//        CoroutineScope(Dispatchers.IO).launch {
//            getCityStored().collect { cityName ->
//                if (!cityName.isNullOrEmpty()) {
//                    runOnUiThread {
//                        //binding.searchView.setQuery(cityName, true)
//                    }
//                }
//            }
//        }

        // initUI()
    }


    //This method initializes the UI
//    private fun initUI() {
//
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                searchByName(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?) = false
//        })
//
//
//        cityViewModel.isCityFragmentVisible.observe(this, Observer {
//            binding.fragmentContainer.isVisible = it
//        })
//
//    }

    //This method is executed when the Query is entered
//    private fun searchByName(query: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            cityViewModel.getCity(query)
//        }
//    }
//
//    //This object is the name of the field saved in DataStore
//    companion object {
//        const val CITY_NAME = "city_name"
//    }
//
//    //This method returns the last city searched
//    private fun getCityStored(): Flow<String?> {
//        return dataStore.data.map { preferences ->
//            preferences[stringPreferencesKey(CITY_NAME)] ?: ""
//        }
//    }
}