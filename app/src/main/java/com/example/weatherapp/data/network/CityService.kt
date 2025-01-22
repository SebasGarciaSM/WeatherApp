package com.example.weatherapp.data.network

import android.util.Log
import com.example.weatherapp.core.RetrofitHelper
import com.example.weatherapp.data.models.CityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CityService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCity(query: String): CityModel? {
        try {
            return withContext(Dispatchers.IO) {

                val retrofitResponse: Response<CityModel> =
                    retrofit.create(CityApiClient::class.java)
                        .getCity(query)

                if (retrofitResponse.body() != null) {
                    retrofitResponse.body()!!
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            Log.i("tag", e.toString())
        }
        return null
    }

}