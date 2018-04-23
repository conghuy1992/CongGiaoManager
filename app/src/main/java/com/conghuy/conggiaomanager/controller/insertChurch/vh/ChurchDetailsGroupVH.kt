package com.conghuy.conggiaomanager.controller.insertChurch.vh

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.DateCallback
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.controller.insertChurch.InsertChurchAdapter
import com.conghuy.conggiaomanager.model.ChurchDto
import com.conghuy.conggiaomanager.view.TextTimeView

import java.util.*

class ChurchDetailsGroupVH(itemView: View, val context: Context, val adapter: InsertChurchAdapter) : RecyclerView.ViewHolder(itemView) {
    val TAG = "ChurchDetailsVH"
    var tvName: TextView? = null
    var tvAddress: TextView? = null
    var layoutOne: LinearLayout? = null
    var layoutTwo: LinearLayout? = null
    var layoutThree: LinearLayout? = null
    var layoutFour: LinearLayout? = null

    init {
        layoutOne = itemView.findViewById<View>(R.id.layoutOne) as LinearLayout
        layoutTwo = itemView.findViewById<View>(R.id.layoutTwo) as LinearLayout
        layoutThree = itemView.findViewById<View>(R.id.layoutThree) as LinearLayout
        layoutFour = itemView.findViewById<View>(R.id.layoutFour) as LinearLayout

        tvName = itemView.findViewById<View>(R.id.tvName) as TextView
        tvAddress = itemView.findViewById<View>(R.id.tvAddress) as TextView
    }

    fun handler(obj: ChurchDto, index: Int) {
        Log.d(TAG, "handler")
//        tv1?.text = Utils.getTimeWithOutEmptyText(obj.normal_day_morning)
//
//        tv2?.text = Utils.getTimeWithOutEmptyText(obj.normal_day_afternoon)
//
//        tv3?.text = Utils.getTimeWithOutEmptyText(obj.special_day_morning)
//
//        tv4?.text = Utils.getTimeWithOutEmptyText(obj.special_day_afternoon)

        layoutOne?.removeAllViews()
        layoutTwo?.removeAllViews()
        layoutThree?.removeAllViews()
        layoutFour?.removeAllViews()

//        TextTimeView
        var listChild = obj.listChild
        for (i in 0 until listChild!!.size) {
            layoutOne?.addView(TextTimeView(context, listChild[i].normal_day_morning))
            layoutTwo?.addView(TextTimeView(context, listChild[i].normal_day_afternoon))
            layoutThree?.addView(TextTimeView(context, listChild[i].special_day_morning))
            layoutFour?.addView(TextTimeView(context, listChild[i].special_day_afternoon))
        }

        if (obj.isShowTitle) {
            tvName?.text = obj.church_name
            tvAddress?.text = obj.ward_name + ", " + obj.districts_name + ", " + obj.province_name
        }
    }
}
