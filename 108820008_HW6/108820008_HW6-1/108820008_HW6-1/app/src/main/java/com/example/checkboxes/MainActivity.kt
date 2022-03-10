package com.example.checkboxes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var checkboxIdList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkboxIdList = listOf(
            R.id.chocolate_syrup_checkbox,
            R.id.sprinkles_checkbox,
            R.id.crushed_nuts_checkbox,
            R.id.cherries_checkbox,
            R.id.orio_cookie_crumbles_checkbox
        )
    }

    fun onSubmit(view: View) {
        var toastMessage = "Toppings: "
        for (checkboxId in checkboxIdList) {
            val checkbox = findViewById<CheckBox>(checkboxId)
            if (checkbox.isChecked) {
                toastMessage += checkbox.text
                toastMessage += ", "
            }
        }
        Toast.makeText(applicationContext, toastMessage.dropLast(2), Toast.LENGTH_SHORT).show()
    }
}