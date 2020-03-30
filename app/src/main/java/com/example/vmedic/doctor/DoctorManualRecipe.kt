package com.example.vmedic.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentDoctorBinding
import com.example.vmedic.databinding.FragmentDoctorManualRecipeBinding
import kotlinx.android.synthetic.main.fragment_doctor_manual_recipe.*
import kotlinx.android.synthetic.main.fragment_principal.*


class DoctorManualRecipe : Fragment() {
    private lateinit var binding: FragmentDoctorManualRecipeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_doctor_manual_recipe, container, false
        )
        //Data base
        val db = DoctorDataBase(context)

        //Functions
        binding.buttonAdd.setOnClickListener{
            //Get info of last request
            val medicine = editTextMedName.getText().toString()
            val dose = editTextDosis.getText().toString()
            val extra = editTextExtra.getText().toString()

            db.insert(Recipe(medicine, dose, extra, ""))

            //Clearing edit text on view after adding the med to recipe
            editTextMedName.getText().clear()
            editTextDosis.getText().clear()
            editTextExtra.getText().clear()
        }

        binding.buttonComplete.setOnClickListener{
            //Returns to doctor main view
            view!!.findNavController().navigate(R.id.action_doctorManualRecipe_to_doctor)
        }
        binding.buttonCancel.setOnClickListener{
            //Returns to doctor main view
            view!!.findNavController().navigate(R.id.action_doctorManualRecipe_to_doctor)
        }


        return binding.root
    }
}
