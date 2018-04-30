package com.here.example.weather

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.experimental.CoroutineContext

class WeatherRepository {

    private val weatherApi: WeatherApi

    private val context: CoroutineContext = CommonPool

    init {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://weather.api.here.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    suspend fun getObservation(lat: Double, lng: Double): WeatherObservation {
        return withContext(context) {
            weatherApi.getObservations("observation", lat, lng, true).execute().body()!!
        }
    }

}
