package com.conghuy.conggiaomanager.controller.insertChurch.vh

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.controller.insertChurch.InsertChurchAdapter

import com.conghuy.conggiaomanager.model.TimeDto

class PlusVH(itemView: View,val adapter: InsertChurchAdapter) : RecyclerView.ViewHolder(itemView) {
    var btnPlus: Button? = null

    init {
        btnPlus = itemView.findViewById<View>(R.id.btnPlus) as Button
    }

    fun handler(obj: TimeDto, index: Int) {
        btnPlus?.setOnClickListener {
            adapter.addRow()
        }
    }
}
