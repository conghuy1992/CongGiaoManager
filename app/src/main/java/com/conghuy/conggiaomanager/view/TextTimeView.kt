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
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ChurchDto


/**
 * Created by Android on 18/1/2018.
 */

class TextTimeView(context: Context, timeMilis: Long) : LinearLayout(context) {
    var tvTime: TextView? = null
    private val TAG = "TextTimeView"

    init {
        init(context, timeMilis)
    }

    private fun init(context: Context, timeMilis: Long) {
        val li = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(R.layout.text_time_view_layout, this, true)
        tvTime = findViewById<View>(R.id.tvTime) as TextView
        tvTime?.text = Utils.getTimeWithOutEmptyText(timeMilis)
    }
}
