package com.example.usernavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openDonutActivity(view: View) {
        val intent = Intent(this, DonutActivity::class.java)
        startActivity(intent)
    }

    fun openFroyoActivity(view: View) {
        val intent = Intent(this, FroyoActivity::class.java)
        startActivity(intent)
    }

    fun openIceCreamActivity(view: View) {
        val intent = Intent(this, IceCreamActivity::class.java)
        startActivity(intent)
    }
}