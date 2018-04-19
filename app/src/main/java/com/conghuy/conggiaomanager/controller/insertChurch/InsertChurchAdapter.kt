package com.conghuy.conggiaomanager.controller.insertChurch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.controller.insertChurch.vh.ChurchDetailsVH
import com.conghuy.conggiaomanager.controller.insertChurch.vh.ChurchNormalVH
import com.conghuy.conggiaomanager.controller.insertChurch.vh.HeaderVH
import com.conghuy.conggiaomanager.controller.insertChurch.vh.PlusVH
import com.conghuy.conggiaomanager.model.ChurchDto

class InsertChurchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var context: Context? = null
    var timeDtoList: MutableList<ChurchDto>? = null
    var TAG = "InsertChurchAdapter"

    constructor() {}
    constructor(context: Context, timeDtoList: MutableList<ChurchDto>) {
        this.context = context
        this.timeDtoList = timeDtoList
    }

    fun update(list: MutableList<ChurchDto>?) {
        this.timeDtoList = list
        notifyDataSetChanged()
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
        } else if (viewType == Statics.Church_title_details) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_details, parent, false)
            vh = HeaderVH(v)
        } else if (viewType == Statics.Church_header_details) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_header_details, parent, false)
            vh = HeaderVH(v)
        } else if (viewType == Statics.Church_details) {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_church_details, parent, false)
            vh = ChurchDetailsVH(v, context!!, this)
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
        } else if (holder is ChurchDetailsVH) {
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
                && obj?.normal_day_morning == 0L
                && obj?.normal_day_afternoon == 0L
                && obj?.special_day_morning == 0L
                && obj?.special_day_afternoon == 0L) {
            return
        }

        timeDtoList?.add(timeDtoList?.size!! - 1, ChurchDto(0, 0))
        this.notifyDataSetChanged()
    }

    fun changedItem(index: Int) {
        notifyItemChanged(index)
    }

    fun getTime(): MutableList<ChurchDto>? {
        return timeDtoList
    }

    fun remove(obj: ChurchDto) {
        var i: Int = 0
        while (i < timeDtoList!!.size) {
            var dto = timeDtoList!![i]
            if (obj.type == 0
                    && obj.normal_day_morning == dto.normal_day_morning
                    && obj.normal_day_afternoon == dto.normal_day_afternoon
                    && obj.special_day_morning == dto.special_day_morning
                    && obj.special_day_afternoon == dto.special_day_afternoon) {
                timeDtoList?.removeAt(i)
                notifyDataSetChanged()
                break
            }
            i++
        }
    }
}