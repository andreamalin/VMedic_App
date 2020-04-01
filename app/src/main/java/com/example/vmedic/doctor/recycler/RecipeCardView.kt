package com.example.vmedic.doctor.recycler

import com.example.vmedic.doctor.DoctorDataBase
import com.example.vmedic.doctor.Recipe
import com.example.vmedic.medicine.recycler.TopSpacingItem


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentMedicineCardViewBinding


class RecipeCardView : Fragment() {
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeList: List<Recipe>
    private lateinit var binding: FragmentMedicineCardViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_medicine_card_view, container, false
        )
        val db = DoctorDataBase(context) //DB
        recipeList = db.getRecipeList()

        //Recycler View
        val recycler = binding.RecyclerViewSeeResults

        recycler.apply {
            layoutManager = LinearLayoutManager(this.context!!)
            val topSpacingDecorator = TopSpacingItem(30)
            addItemDecoration(topSpacingDecorator)
            recipeAdapter = RecipeAdapter()
            adapter = recipeAdapter
            recipeAdapter.submitList(recipeList)

        }

        return binding.root
    }


}
