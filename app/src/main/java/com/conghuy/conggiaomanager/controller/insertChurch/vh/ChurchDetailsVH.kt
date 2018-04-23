package com.conghuy.conggiaomanager.controller.insertChurch.vh

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.DateCallback
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.controller.insertChurch.InsertChurchAdapter
import com.conghuy.conggiaomanager.model.ChurchDto

import java.util.*

class ChurchDetailsVH(itemView: View, val context: Context, val adapter: InsertChurchAdapter) : RecyclerView.ViewHolder(itemView) {
    val TAG = "ChurchDetailsVH"
    var tv1: TextView? = null
    var tv2: TextView? = null
    var tv3: TextView? = null
    var tv4: TextView? = null
    var tvName: TextView? = null
    var tvAddress: TextView? = null

    init {
        tv1 = itemView.findViewById<View>(R.id.tv1) as TextView
        tv2 = itemView.findViewById<View>(R.id.tv2) as TextView
        tv3 = itemView.findViewById<View>(R.id.tv3) as TextView
        tv4 = itemView.findViewById<View>(R.id.tv4) as TextView
        tvName = itemView.findViewById<View>(R.id.tvName) as TextView
        tvAddress = itemView.findViewById<View>(R.id.tvAddress) as TextView
    }

    fun handler(obj: ChurchDto, index: Int) {
        Log.d(TAG, "handler")
        tv1?.text = Utils.getTimeWithOutEmptyText(obj.normal_day_morning)

        tv2?.text = Utils.getTimeWithOutEmptyText(obj.normal_day_afternoon)

        tv3?.text = Utils.getTimeWithOutEmptyText(obj.special_day_morning)

        tv4?.text = Utils.getTimeWithOutEmptyText(obj.special_day_afternoon)



        if (obj.isShowTitle){
            tvName?.text = obj.church_name
            tvAddress?.text = obj.ward_name + ", " + obj.districts_name + ", " + obj.province_name
        }

//        tv1?.setOnClickListener {
//            showTimeDialog(obj, index, 1)
//        }
//        tv2?.setOnClickListener {
//            showTimeDialog(obj, index, 2)
//        }
//        tv3?.setOnClickListener {
//            showTimeDialog(obj, index, 3)
//        }
//        tv4?.setOnClickListener {
//            showTimeDialog(obj, index, 4)
//        }
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
