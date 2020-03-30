package com.example.vmedic.admin

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentAdminBinding
import com.example.vmedic.medicine.Medicine
import com.example.vmedic.medicine.MedicineDataBase
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_principal.*

class Admin: Fragment() {
    private lateinit var binding: FragmentAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_admin, container, false
        )
        super.onCreate(savedInstanceState)

        binding.buttonAgregar.setOnClickListener {
            val db = MedicineDataBase(context)
            val name = et1.getText().toString()
            val presentation = et2.getText().toString()
            val amount = et3.getText().toString().toInt()
            val dueDate = et4.getText().toString()
            val location = et5.getText().toString()

            db.insert(Medicine(name, amount, presentation, dueDate, location))
            Toast.makeText(
                activity,
                "Los medicamentos se han añadido correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.buttonInventario.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_admin_to_principalMedicine)
        }
        return binding.root
    }
}