package com.example.vmedic.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentPrincipalBinding
import kotlinx.android.synthetic.main.fragment_principal.*

class Principal : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val db = VMedicDataBase(context) //DB
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_principal, container, false
        )
        //Keyboard
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        createAccounts() //Creating accounts
        //Spinner
        val adapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item, db.getOrg())
        binding.spinnerOrg.adapter = adapter

        //Accepting account
        binding.buttonAccept.setOnClickListener{
            //Get info of last account
            val username = editTextUsername.getText().toString()
            val password = editTextPassword.getText().toString()
            val org = spinnerOrg.getSelectedItem().toString()

            if (db.getUser(username, password, org)){
                val job = db.getJob()
                Toast.makeText(activity, "Hola $username :) puesto $job", Toast.LENGTH_SHORT).show()

                if (job == "Admin"){
                    view!!.findNavController().navigate(R.id.action_principal_to_admin)
                } else if (job == "Doctor"){
                    view!!.findNavController().navigate(R.id.action_principal_to_doctor)
                } else {
                    view!!.findNavController().navigate(R.id.action_principal_to_volunteer)
                }

            } else {
                Toast.makeText(activity, "El usuario $username no fue encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }




    //Create accounts
    private fun createAccounts(){
        //Db
        val db = VMedicDataBase(context)

        db.insert(User("Andrea", "hola123", "Admin", "Cruz Roja"))
        db.insert(User("Laura", "adios123", "Doctor", "Cruz Roja"))
        db.insert(User("Oliver", "12345", "Voluntario", "Casa de Dios"))
        db.insert(User("Marco", "morado", "Voluntario", "Cruz Roja"))
    }
}
