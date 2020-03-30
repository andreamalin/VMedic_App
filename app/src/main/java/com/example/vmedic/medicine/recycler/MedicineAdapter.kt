package com.example.vmedic.medicine.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vmedic.R
import com.example.vmedic.medicine.MedicineDataBase
/**
class MedicineAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var item: List<MedicineDataBase> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context!!).inflate(R.layout.medicine_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val actual = item.get(position)

        when(holder){
            is AnswerViewHolder -> {
                holder.bind(item.get(position))
            }
        }


    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun submitList(repositoryList: List<MedicineDataBase>){
        item = repositoryList
    }

    class AnswerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val repository_name = itemView.repositoryName


        fun bind(rep: RepositoryDataBase){
            repository_name.setText(rep.name)
        }
    }


}
*/