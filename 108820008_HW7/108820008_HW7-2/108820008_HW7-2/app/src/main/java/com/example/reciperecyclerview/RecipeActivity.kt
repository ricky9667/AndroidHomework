package com.example.reciperecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecipeActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        titleTextView = findViewById(R.id.title_text_view)
        contentTextView = findViewById(R.id.content_text_view)

        val title = intent.getStringExtra("name").toString()
        titleTextView.text = title

        val content = intent.getStringExtra("description").toString() + "\n\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        contentTextView.text = content
    }
}