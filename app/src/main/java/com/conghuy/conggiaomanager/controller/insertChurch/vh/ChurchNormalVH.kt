package com.conghuy.conggiaomanager.controller.insertChurch.vh

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.DateCallback
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.controller.insertChurch.InsertChurchAdapter

import com.conghuy.conggiaomanager.model.TimeDto
import java.util.*

class ChurchNormalVH(itemView: View, val context: Context, val adapter: InsertChurchAdapter) : RecyclerView.ViewHolder(itemView) {
    var tv1: TextView? = null
    var tv2: TextView? = null
    var tv3: TextView? = null
    var tv4: TextView? = null

    init {
        tv1 = itemView.findViewById<View>(R.id.tv1) as TextView
        tv2 = itemView.findViewById<View>(R.id.tv2) as TextView
        tv3 = itemView.findViewById<View>(R.id.tv3) as TextView
        tv4 = itemView.findViewById<View>(R.id.tv4) as TextView
    }

    fun handler(obj: TimeDto, index: Int) {


        tv1?.text = Utils.getTime(context,obj.timeOne)

        tv2?.text = Utils.getTime(context,obj.timeTwo)

        tv3?.text = Utils.getTime(context,obj.timeThree)

        tv4?.text = Utils.getTime(context,obj.timeFour)

        tv1?.setOnClickListener {
            showTimeDialog(obj, index, 1)
        }
        tv2?.setOnClickListener {
            showTimeDialog(obj, index, 2)
        }
        tv3?.setOnClickListener {
            showTimeDialog(obj, index, 3)
        }
        tv4?.setOnClickListener {
            showTimeDialog(obj, index, 4)
        }
    }

    fun showTimeDialog(obj: TimeDto, index: Int, type: Int) {
        var cal = Calendar.getInstance()

        if (type == 1 && obj.timeOne > 0) cal.timeInMillis = obj.timeOne
        else if (type == 2 && obj.timeTwo > 0) cal.timeInMillis = obj.timeTwo
        else if (type == 3 && obj.timeThree > 0) cal.timeInMillis = obj.timeThree
        else if (type == 4 && obj.timeFour > 0) cal.timeInMillis = obj.timeFour

        Utils.showTimeDialog(context, cal, object : DateCallback {
            override fun onFinish(calendar: Calendar) {
                if (type == 1) obj.timeOne = calendar.timeInMillis
                else if (type == 2) obj.timeTwo = calendar.timeInMillis
                else if (type == 3) obj.timeThree = calendar.timeInMillis
                else if (type == 4) obj.timeFour = calendar.timeInMillis
                adapter.changedItem(index)
            }
        })
    }
}
