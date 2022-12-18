package com.redmechax00.vkcup2022qualification.ui.temporary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.redmechax00.vkcup2022qualification.R

class TemporaryFinishAdapter :
    RecyclerView.Adapter<TemporaryFinishAdapter.TemporaryFinishHolder>() {

    private val data: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemporaryFinishHolder {
        return TemporaryFinishHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.temporary_finish_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: TemporaryFinishHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun updateData(newData: ArrayList<String>) {
        data.addAll(newData)
    }

    class TemporaryFinishHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemTitle = itemView.findViewById<TextView>(R.id.temporary_finish_item_text_title)

        fun bind(item: String) {
            itemTitle.text = item
        }
    }
}