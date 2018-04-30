package com.here.example.weather

import com.squareup.moshi.Json

data class WeatherObservation(
        @Json(name = "observations") val observations: Observations = Observations(),
        @Json(name = "feedCreation") val feedCreation: String = "",
        @Json(name = "metric") val metric: Boolean = false
)

data class Observations(
        @Json(name = "location") val location: List<Location> = listOf()
)

data class Location(
        @Json(name = "observation") val observation: List<Observation> = listOf(),
        @Json(name = "country") val country: String = "",
        @Json(name = "state") val state: String = "",
        @Json(name = "city") val city: String = "",
        @Json(name = "latitude") val latitude: Double = 0.0,
        @Json(name = "longitude") val longitude: Double = 0.0,
        @Json(name = "distance") val distance: Double = 0.0,
        @Json(name = "timezone") val timezone: Int = 0
)

data class Observation(
        @Json(name = "daylight") val daylight: String = "",
        @Json(name = "description") val description: String = "",
        @Json(name = "skyInfo") val skyInfo: String = "",
        @Json(name = "skyDescription") val skyDescription: String = "",
        @Json(name = "temperature") val temperature: String = "",
        @Json(name = "temperatureDesc") val temperatureDesc: String = "",
        @Json(name = "comfort") val comfort: String = "",
        @Json(name = "highTemperature") val highTemperature: String = "",
        @Json(name = "lowTemperature") val lowTemperature: String = "",
        @Json(name = "humidity") val humidity: String = "",
        @Json(name = "dewPoint") val dewPoint: String = "",
        @Json(name = "precipitation1H") val precipitation1H: String = "",
        @Json(name = "precipitation3H") val precipitation3H: String = "",
        @Json(name = "precipitation6H") val precipitation6H: String = "",
        @Json(name = "precipitation12H") val precipitation12H: String = "",
        @Json(name = "precipitation24H") val precipitation24H: String = "",
        @Json(name = "precipitationDesc") val precipitationDesc: String = "",
        @Json(name = "airInfo") val airInfo: String = "",
        @Json(name = "airDescription") val airDescription: String = "",
        @Json(name = "windSpeed") val windSpeed: String = "",
        @Json(name = "windDirection") val windDirection: String = "",
        @Json(name = "windDesc") val windDesc: String = "",
        @Json(name = "windDescShort") val windDescShort: String = "",
        @Json(name = "barometerPressure") val barometerPressure: String = "",
        @Json(name = "barometerTrend") val barometerTrend: String = "",
        @Json(name = "visibility") val visibility: String = "",
        @Json(name = "snowCover") val snowCover: String = "",
        @Json(name = "icon") val icon: String = "",
        @Json(name = "iconName") val iconName: String = "",
        @Json(name = "iconLink") val iconLink: String = "",
        @Json(name = "ageMinutes") val ageMinutes: String = "",
        @Json(name = "activeAlerts") val activeAlerts: String = "",
        @Json(name = "country") val country: String = "",
        @Json(name = "state") val state: String = "",
        @Json(name = "city") val city: String = "",
        @Json(name = "latitude") val latitude: Double = 0.0,
        @Json(name = "longitude") val longitude: Double = 0.0,
        @Json(name = "distance") val distance: Double = 0.0,
        @Json(name = "elevation") val elevation: Int = 0,
        @Json(name = "utcTime") val utcTime: String = ""
)
