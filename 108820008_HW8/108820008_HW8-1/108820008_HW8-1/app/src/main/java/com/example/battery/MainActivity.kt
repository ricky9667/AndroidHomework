package com.example.battery

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var batteryImageView: ImageView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private var batteryLevel = 4
    private val minBatteryLevel = 0
    private val maxBatteryLevel = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        batteryImageView = findViewById(R.id.image_battery)
        plusButton = findViewById(R.id.button_plus)
        minusButton = findViewById(R.id.button_minus)
        batteryImageView.setImageLevel(batteryLevel)

        plusButton.setOnClickListener {
            batteryLevel =
                if (batteryLevel + 1 > maxBatteryLevel) maxBatteryLevel else batteryLevel + 1
            batteryImageView.setImageLevel(batteryLevel)
        }

        minusButton.setOnClickListener {
            batteryLevel =
                if (batteryLevel - 1 < minBatteryLevel) minBatteryLevel else batteryLevel - 1
            batteryImageView.setImageLevel(batteryLevel)
        }
    }
}