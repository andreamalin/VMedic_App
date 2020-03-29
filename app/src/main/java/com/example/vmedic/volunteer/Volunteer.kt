package com.example.vmedic.volunteer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentVolunteerBinding


class Volunteer : Fragment() {
    private lateinit var binding: FragmentVolunteerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_volunteer, container, false
        )

        return binding.root
    }
}
