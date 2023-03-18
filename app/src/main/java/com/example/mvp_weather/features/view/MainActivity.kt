package com.example.mvp_weather.features.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp_weather.R

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}