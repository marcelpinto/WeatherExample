package com.here.example.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {

    private val weatherRepository = WeatherRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.checkButton).setOnClickListener {
            launch(UI) {
                // Show loading
                val data = weatherRepository.getObservation(52.1, 13.5)
                // process the data on the UI thread
                findViewById<TextView>(R.id.weatherInfo).text = data.toString()
            }
        }
    }
}
