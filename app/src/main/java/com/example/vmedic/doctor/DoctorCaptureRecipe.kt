package com.example.vmedic.doctor

import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentDoctorCaptureRecipeBinding
import kotlinx.android.synthetic.main.fragment_doctor_capture_recipe.*

//Code reference: Atif Pervaiz
//https://www.youtube.com/watch?v=3gkAoF90RZ4

class DoctorCaptureRecipe : Fragment() {
    private lateinit var binding: FragmentDoctorCaptureRecipeBinding
    private var PERMISSION_CODE: Int = 1000
    private var IMAGE_CAPTURE_CODE: Int = 1001
    var image_uri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_doctor_capture_recipe, container, false
        )
        //Data base
        val db = DoctorDataBase(context)

        binding.buttonCaptureRecipe.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(context!!, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(context!!, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){


                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    openCamera()
                }
            }
        }

        binding.buttonSendRecipe.setOnClickListener{
            val imageRecipe = image_uri.toString()

            db.insert(Recipe("", 0, "", imageRecipe))
            view!!.findNavController().navigate(R.id.action_doctorCaptureRecipe_to_doctor)
        }

        binding.buttonCancel.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_doctorCaptureRecipe_to_doctor)
        }

        return binding.root
    }

    private fun openCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Recipe")
        values.put(MediaStore.Images.Media.DESCRIPTION, "New recipe from doctor")
        image_uri = context!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera()

                } else {
                    Toast.makeText(activity, "No tenemos permiso para acceder a la camara", Toast.LENGTH_SHORT).show()

                }
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            binding.imageViewRecipe.setImageURI(image_uri)
        }
    }

}
