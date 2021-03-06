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
import com.conghuy.conggiaomanager.model.ChurchDto

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

    fun handler(obj: ChurchDto, index: Int) {


        tv1?.text = Utils.getTime(context,obj.normal_day_morning)

        tv2?.text = Utils.getTime(context,obj.normal_day_afternoon)

        tv3?.text = Utils.getTime(context,obj.special_day_morning)

        tv4?.text = Utils.getTime(context,obj.special_day_afternoon)

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

    fun showTimeDialog(obj: ChurchDto, index: Int, type: Int) {
        var cal = Calendar.getInstance()

        if (type == 1 && obj.normal_day_morning > 0) cal.timeInMillis = obj.normal_day_morning
        else if (type == 2 && obj.normal_day_afternoon > 0) cal.timeInMillis = obj.normal_day_afternoon
        else if (type == 3 && obj.special_day_morning > 0) cal.timeInMillis = obj.special_day_morning
        else if (type == 4 && obj.special_day_afternoon > 0) cal.timeInMillis = obj.special_day_afternoon

        Utils.showTimeDialog(context, cal, object : DateCallback {
            override fun onFinish(calendar: Calendar) {
                if (type == 1) obj.normal_day_morning = calendar.timeInMillis
                else if (type == 2) obj.normal_day_afternoon = calendar.timeInMillis
                else if (type == 3) obj.special_day_morning = calendar.timeInMillis
                else if (type == 4) obj.special_day_afternoon = calendar.timeInMillis
                adapter.changedItem(index)
            }
        })
    }
}
