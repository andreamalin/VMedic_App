package com.example.vmedic.medicine.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vmedic.R
import com.example.vmedic.databinding.FragmentMedicineCardViewBinding
import com.example.vmedic.medicine.Medicine
import com.example.vmedic.medicine.MedicineDataBase
import kotlinx.android.synthetic.main.medicine_card_view.*

class MedicineCardView : Fragment() {
    private lateinit var medicineAdapter: MedicineAdapter
    private lateinit var medicineList: List<Medicine>
    private lateinit var binding: FragmentMedicineCardViewBinding
    private lateinit var medicineItem: Medicine

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_medicine_card_view, container, false
        )
        var name = ""

        val db = MedicineDataBase(context) //DB
        medicineList = db.getMedicineList()

        //Recycler View
        val recycler = binding.RecyclerViewSeeResults

        //Get comment
        arguments?.let {
            name = it.getString("actual_med")!!
        }

        if (name!=""){
            val newMedicineList = ArrayList<Medicine>()
            for (item in medicineList){
                if (item.name == name){
                    medicineItem = item
                    newMedicineList.add(medicineItem)
                    medicineList = newMedicineList
                }
            }
        }


        recycler.apply {
            layoutManager = LinearLayoutManager(this.context!!)
            val topSpacingDecorator = TopSpacingItem(30)
            addItemDecoration(topSpacingDecorator)
            medicineAdapter = MedicineAdapter()
            adapter = medicineAdapter
            medicineAdapter.submitList(medicineList)

        }

        return binding.root
    }


}
