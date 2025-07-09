package com.example.weatherapp.ui.fragments

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment() {
//    private val cityViewModel: CityViewModel by activityViewModels()
//    private lateinit var binding: FragmentCityBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentCityBinding.inflate(inflater, container, false)
//
//        lifecycleScope.launch {
//            dataStore.data.collect { preferences ->
//                preferences[stringPreferencesKey(CITY_NAME)]
//            }
//        }
//
//        initFragmentUI()
//
//        return binding.root
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun initFragmentUI() {
//        cityViewModel.cityState.observe(viewLifecycleOwner) { result ->
//            when (result) {
//                is DomainState.Loading -> {
//                    //When data is Loading, UI shows Progress Bar
//
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//
//                is DomainState.Success -> {
//                    //When data is successfully received, the Progress Bar and Fragment need to be hidden
//                    binding.progressBar.visibility = View.GONE
//                    cityViewModel.isCityFragmentVisible.postValue(true)
//
//
//                    //When data is successfully received, it gets assign to all the fields in the UI
//                    val city = result.data
//                    binding.tvCityName.text = city.name
//                    binding.tvTemp.text = city.temperatureInCelsius.appendDegreeFormat()
//                    binding.tvFeelsLike.text = city.feelsLikeInCelsius.appendDegreeFormat()
//                    binding.tvMaxTemp.text = city.maxTempInCelsius.appendDegreeFormat()
//                    binding.tvMinTemp.text = city.minTempInCelsius.appendDegreeFormat()
//                    binding.tvPressure.text = city.pressure.appendPercentageFormat()
//                    binding.tvHumidity.text = city.humidity.appendPercentageFormat()
//                    binding.tvSeaLevel.text = city.seaLevel.appendPercentageFormat()
//
//                    binding.tvSpeed.text = city.wind.speed.appendMetersPerSecondFormat()
//                    binding.tvGust.text = city.wind.gust.appendMetersPerSecondFormat()
//                    binding.tvDegrees.text = city.wind.degrees.appendDegreeFormat()
//
//                    val currentWeatherIcon = city.icon
//                    Picasso.get()
//                        .load("https://openweathermap.org/img/wn/$currentWeatherIcon@4x.png")
//                        .into(binding.ivWeatherIcon)
//
//                    //This coroutine saves the data into DataStore
//                    lifecycleScope.launch {
//                        saveCityName(city.name)
//                    }
//                }
//
//                is DomainState.Error -> {
//                    //When data returns an error,
//                    //the Progress Bar is hidden and the UI shows a Toast with the error message
//                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    //Access to the Context of the Activity
//    private val dataStore by lazy {
//        requireContext().dataStore
//    }
//
//    //Saves City Name on DataStore
//    private suspend fun saveCityName(value: String) {
//        dataStore.edit { preferences ->
//            preferences[stringPreferencesKey(CITY_NAME)] = value
//        }
//    }
}