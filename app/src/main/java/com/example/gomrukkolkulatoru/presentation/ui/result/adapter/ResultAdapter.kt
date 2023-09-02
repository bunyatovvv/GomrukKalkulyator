package com.example.gomrukkolkulatoru.presentation.ui.result.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.domain.model.DutyModel

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    class ResultViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DutyModel>(){
        override fun areItemsTheSame(oldItem: DutyModel, newItem: DutyModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: DutyModel, newItem: DutyModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_result,parent,false)
        )
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {


        val result = differ.currentList[position]
        val name = holder.itemView.findViewById<TextView>(R.id.nameText)
        val value = holder.itemView.findViewById<TextView>(R.id.valueText)

        name.text = result.name
        value.text = result.value.toString()

    }
}