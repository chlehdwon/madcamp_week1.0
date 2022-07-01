package com.example.madcamp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (private var list: MutableList<TestData>): RecyclerView.Adapter<ListAdapter.ListItemViewHolder> (){

    inner class ListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var data1Text: TextView = itemView!!.findViewById(R.id.data1Text)
//        var data2Text: TextView = itemView!!.findViewById(R.id.data2Text)
//        var data3Text: TextView = itemView!!.findViewById(R.id.data3Text)

        // onBindViewHolder의 역할을 대신한다.
        fun bind(data: TestData, position: Int) {
            data1Text.text = data.getData1()
//            data2Text.text = data.getData2()
//            data3Text.text = data.getData3()
        }
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_list, parent, false)
        val vHolder  = ListItemViewHolder(view)

        val dialog: Dialog = Dialog(parent.context)
        dialog.setContentView(R.layout.contact_popup)

        vHolder.itemView.setOnClickListener {
            var dialog_name: TextView = dialog!!.findViewById(R.id.dialog_name)
            var dialog_number: TextView = dialog!!.findViewById(R.id.dialog_number)
            dialog_name.setText(list.get(vHolder.adapterPosition).getData1())
            dialog_number.setText(list.get(vHolder.adapterPosition).getData2())

            dialog.show()
        }


        return vHolder
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: ListAdapter.ListItemViewHolder, position: Int) {
        holder.bind(list[position], position)
    }


}