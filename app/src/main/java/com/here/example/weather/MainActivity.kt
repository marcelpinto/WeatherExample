package com.here.example.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {

    private val weatherRepository = WeatherRepository()

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkButton.setOnClickListener { button ->
            job = launch(UI) {
                // Show loading
                button.isEnabled = false
                weatherInfo.text = "Loading..."

                // Network call
                val lat = latitudeInput.text.toString().toDouble()
                val lng = longitudeInput.text.toString().toDouble()
                val response = weatherRepository.getObservation(lat, lng)

                // process the data on the UI thread
                val displayText = when (response) {
                    is ApiResponse.Success<WeatherObservation> -> {
                        response.result.observations.location.first().observation.first().run {
                            "$description in $city with a temperature of ${temperature.toDouble().toInt()}°C" +
                                    "\n\nMeta information: ${toString()}"
                        }
                    }
                    is ApiResponse.Failure -> "Invalid call error: ${response.code}"
                    is ApiResponse.Exception -> "There was an error ${response.throwable}"
                }
                weatherInfo.text = displayText
                button.isEnabled = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
