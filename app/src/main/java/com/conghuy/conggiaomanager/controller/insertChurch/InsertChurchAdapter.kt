package com.conghuy.conggiaomanager.controller.insertChurch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.controller.insertChurch.vh.ChurchNormalVH
import com.conghuy.conggiaomanager.controller.insertChurch.vh.HeaderVH
import com.conghuy.conggiaomanager.controller.insertChurch.vh.PlusVH
import com.conghuy.conggiaomanager.model.TimeDto

class InsertChurchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var context: Context? = null
    var timeDtoList: MutableList<TimeDto>? = null
    var TAG = "InsertChurchAdapter"

    constructor() {}
    constructor(context: Context, timeDtoList: MutableList<TimeDto>) {
        this.context = context
        this.timeDtoList = timeDtoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        if (viewType == Statics.Church_header) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_normal_bold, parent, false)
            vh = HeaderVH(v)
        } else if (viewType == Statics.Church_plus) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_plus, parent, false)
            vh = PlusVH(v, this)
        } else if (viewType == Statics.Church_title) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_header, parent, false)
            vh = HeaderVH(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_normal, parent, false)
            vh = ChurchNormalVH(v, context!!, this)
        }
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = timeDtoList!![position]
        if (holder is ChurchNormalVH) {
            holder.handler(obj, position)
        } else if (holder is PlusVH) {
            holder.handler(obj, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return timeDtoList!![position].type
    }

    override fun getItemCount(): Int {
        return timeDtoList!!.size
    }

    fun addRow() {

        var obj = timeDtoList?.get(timeDtoList!!.size!! - 2)
//        Log.d(TAG,"obj:"+Gson().toJson(obj))

        if (obj?.type == 0
                && obj?.timeOne == 0L
                && obj?.timeTwo == 0L
                && obj?.timeThree == 0L
                && obj?.timeFour == 0L) {
            return
        }

        timeDtoList?.add(timeDtoList?.size!! - 1, TimeDto(0, 0))
        this.notifyDataSetChanged()
    }

    fun changedItem(index: Int) {
        notifyItemChanged(index)
    }

    fun getTime(): MutableList<TimeDto>? {
        return timeDtoList
    }

    fun remove(obj: TimeDto) {
        var i: Int = 0
        while (i < timeDtoList!!.size) {
            var dto = timeDtoList!![i]
            if (obj.type == 0
                    && obj.timeOne == dto.timeOne
                    && obj.timeTwo == dto.timeTwo
                    && obj.timeThree == dto.timeThree
                    && obj.timeFour == dto.timeFour)
            {
                timeDtoList?.removeAt(i)
                notifyDataSetChanged()
                break
            }
                i++
        }
    }
}