package com.here.example.weather

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception
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

    suspend fun getObservation(lat: Double, lng: Double): ApiResponse<WeatherObservation, Any> {
        return withContext(context) {
            try {
                val response = weatherApi.getObservations("observation", lat, lng, true).execute()
                if (response.isSuccessful && response.body() != null) {
                    ApiResponse.Success(response.body()!!)
                } else {
                    ApiResponse.Failure<WeatherObservation, Any>(response.code(), response.errorBody())
                }
            } catch (e: Exception) {
                ApiResponse.Exception<WeatherObservation, Any>(e)
            }
        }
    }
}

sealed class ApiResponse<out T, out S> {

    data class Success<out T>(val result: T) : ApiResponse<T, Any>()

    data class Failure<out T, out S>(val code: Int, val errorBody: S? = null) : ApiResponse<T, S>()

    data class Exception<out T, out S>(val throwable: Throwable) : ApiResponse<T, S>()
}
