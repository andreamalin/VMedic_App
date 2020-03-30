package com.example.vmedic.admin

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentAdminBinding
import kotlinx.android.synthetic.main.fragment_admin.*

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
            val admi = AdminSQLiteOpenHelper(context!!, "Medicamentos", null, 1)
            val db = admi.writableDatabase
            val registro = ContentValues()
            registro.put("Nombre", et1.getText().toString())
            registro.put("Presentación", et2.getText().toString())
            registro.put("Descripcion", et3.getText().toString())
            db.insert("medicamentos", null, registro)
            db.close()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            Toast.makeText(
                activity,
                "Los medicamentos se han añadido correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }
}