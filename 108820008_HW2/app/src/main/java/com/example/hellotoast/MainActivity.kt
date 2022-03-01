package com.example.hellotoast

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mCount: Int = 0
    private lateinit var mShowCount: TextView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowCount = findViewById(R.id.show_count)
        resetButton = findViewById(R.id.button_zero)
    }

    fun showToast(view: View) {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun countUp(view: View) {
        mCount++
        mShowCount.text = mCount.toString()

        val color = if (mCount % 2 == 0) R.color.purple_500 else R.color.teal_700
        view.setBackgroundColor(resources.getColor(color))
        resetButton.setBackgroundColor(resources.getColor(R.color.teal_700))
    }

    fun resetCount(view: View) {
        mCount = 0
        mShowCount.text = mCount.toString()
        resetButton.setBackgroundColor(resources.getColor(R.color.gray))
    }
}