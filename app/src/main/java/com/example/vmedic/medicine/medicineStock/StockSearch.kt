package com.example.vmedic.medicine.medicineStock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentStockSearchBinding

class StockSearch : Fragment() {
    private lateinit var binding: FragmentStockSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_stock_search, container, false
        )

        return binding.root
    }


}