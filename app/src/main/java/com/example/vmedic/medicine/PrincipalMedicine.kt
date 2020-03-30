package com.example.vmedic.medicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentStockLocationBinding
import kotlinx.android.synthetic.main.fragment_stock_location.*

class PrincipalMedicine : Fragment() {
    private lateinit var binding: FragmentStockLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = MedicineDataBase(context) //DB
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock_location, container, false
        )
        //Spinner
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, db.getPresentation())
        binding.spinnerPresentation.adapter = adapter

        //Accepting account
        binding.buttonSearch.setOnClickListener {
            //Get info of last account
            val name = editTextMedicine.getText().toString()
            val presentation = spinnerPresentation.getSelectedItem().toString()

            if (db.getMedicine(name, presentation)) {
                val actualMed = name
                val bundle = bundleOf ("actual_med" to actualMed)
                view!!.findNavController().navigate(R.id.action_principalMedicine_to_medicineCardView, bundle)


            } else {
                Toast.makeText(
                    activity,
                    "El medicamento $name no fue encontrado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonSeeAll.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_principalMedicine_to_medicineCardView)
        }

        return binding.root
    }
}