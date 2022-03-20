package com.example.reciperecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var _recyclerView: RecyclerView
    private lateinit var _recipeListAdapter: RecipeListAdapter
    private val _recipeList: Array<Recipe> = arrayOf(
        Recipe("Android", "This is recipe of Android"),
        Recipe("Beta", "This is recipe of Beta"),
        Recipe("Cupcake", "This is recipe of Cupcake"),
        Recipe("Donut", "This is recipe of Donut"),
        Recipe("Éclair", "This is recipe of Éclair"),
        Recipe("Froyo", "This is recipe of Froyo"),
        Recipe("Gingerbread", "This is recipe of Gingerbread"),
        Recipe("Honeycomb", "This is recipe of Honeycomb"),
        Recipe("Ice Cream Sandwich", "This is recipe of Ice Cream Sandwich"),
        Recipe("Jelly Bean", "This is recipe of Jelly Bean"),
        Recipe("KitKat", "This is recipe of KitKat"),
        Recipe("Lollipop", "This is recipe of Lollipop"),
        Recipe("Marshmallow", "This is recipe of Marshmallow"),
        Recipe("Nougat", "This is recipe of Nougat"),
        Recipe("Oreo", "This is recipe of Oreo"),
        Recipe("Pie", "This is recipe of Pie"),
        Recipe("Q", "This is recipe of Q"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _recipeListAdapter = RecipeListAdapter(_recipeList)
        _recyclerView = findViewById(R.id.recyclerView)
        _recyclerView.adapter = _recipeListAdapter
        _recyclerView.layoutManager = LinearLayoutManager(this)
    }
}