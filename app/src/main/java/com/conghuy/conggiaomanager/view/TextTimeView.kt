package com.conghuy.conggiaomanager.view

/**
 * Created by Android on 3/30/2018.
 */

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ChurchDto


/**
 * Created by Android on 18/1/2018.
 */

class TextTimeView(context: Context, obj: ChurchDto, type: Int) : LinearLayout(context) {
    var tvTime: TextView? = null
    private val TAG = "TextTimeView"

    init {
        init(context, obj, type)
    }

    private fun init(context: Context, obj: ChurchDto, type: Int) {
        val li = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(R.layout.text_time_view_layout, this, true)
        tvTime = findViewById<View>(R.id.tvTime) as TextView

        var color = Utils.getColor(context, R.color.colorBlack)
        if (obj.status == Statics.church_pending) color = Utils.getColor(context, R.color.colorPending)

        var timeMilis = obj.normal_day_morning
        if (type == 2) timeMilis = obj.normal_day_afternoon
        if (type == 3) timeMilis = obj.special_day_morning
        if (type == 4) timeMilis = obj.special_day_afternoon

        tvTime?.text = Utils.getTimeWithOutEmptyText(timeMilis)
        tvTime?.setTextColor(color)
    }
}
