package com.here.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/weather/1.0/report.json")
    fun getObservations(@Query("product") product: String,
                        @Query("latitude") latitude: Double,
                        @Query("longitude") longitude: Double,
                        @Query("oneobservation") single: Boolean
    ): Call<WeatherObservation>
}
