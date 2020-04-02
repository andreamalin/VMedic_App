package com.example.vmedic.medicine.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vmedic.R
import com.example.vmedic.medicine.Medicine
import com.example.vmedic.medicine.MedicineDataBase
import kotlinx.android.synthetic.main.medicine_card_view.view.*

class MedicineAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var item: List<Medicine> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context!!).inflate(R.layout.medicine_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is AnswerViewHolder -> {
                holder.bind(item.get(position))
            }
        }


    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun submitList(medicineList: List<Medicine>){
        item = medicineList
    }

    class AnswerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.medicineName!!
        val due = itemView.dueDate!!
        val amount = itemView.amount!!
        val localization = itemView.localization!!
        val presentation = itemView.presentation!!




        fun bind(med: Medicine){
            name.setText(med.name)
            due.setText(med.dueDate)
            amount.setText(med.amount.toString())
            localization.setText(med.location)
            presentation.setText(med.presentation)

        }
    }


}