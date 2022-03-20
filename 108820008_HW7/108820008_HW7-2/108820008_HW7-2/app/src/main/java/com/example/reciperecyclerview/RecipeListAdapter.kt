package com.example.reciperecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeListAdapter(private val recipeList: Array<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ), View.OnClickListener {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val recipe = recipeList[layoutPosition]
            if (view != null) {
                val intent = Intent(view.context, RecipeActivity::class.java).apply {
                    putExtra("name", recipe.name)
                    putExtra("description", recipe.description)
                }
                view.context?.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.nameTextView.text = recipeList[position].name
        holder.descriptionTextView.text = recipeList[position].description
    }

    override fun getItemCount(): Int = recipeList.size

}
