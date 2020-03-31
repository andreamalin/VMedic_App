package com.example.vmedic.medicine.recycler

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vmedic.R
import com.example.vmedic.doctor.Recipe
import kotlinx.android.synthetic.main.medicine_card_view.view.amount
import kotlinx.android.synthetic.main.recipe_card_view.view.*

class RecipeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var item: List<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context!!).inflate(R.layout.recipe_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is AnswerViewHolder -> {
                holder.bind(item.get(position))
            }
        }


    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun submitList(medicineList: List<Recipe>){
        item = medicineList
    }

    class AnswerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.medName!!
        val pres = itemView.present!!
        val amount = itemView.amount!!
        val recipeImage = itemView.recipeImage!!


        fun bind(recipe: Recipe){
            name.setText(recipe.medicine)
            pres.setText(recipe.presentation)
            amount.setText(recipe.dose.toString())

            Glide.with(itemView.context)
                .load(Uri.parse(recipe.image))
                .into(recipeImage)
        }
    }


}