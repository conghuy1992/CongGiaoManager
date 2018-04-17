package com.conghuy.conggiaomanager.controller.leftMenu

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.conghuy.conggiaomanager.R

class LeftMenuAdapter : RecyclerView.Adapter<LeftMenuAdapter.MyViewHolder> {
    val TAG = "LeftMenuAdapter"
    var list: List<MenuDto>? = null
    var context: Context? = null
    var callBack: MenuCallBack? = null

//    constructor() {}

    constructor(context: Context, groupDtoList: List<MenuDto>, callBack: MenuCallBack) {
        this.context = context
        this.list = groupDtoList
        this.callBack = callBack
    }

    fun updateList(menuDtoList: List<MenuDto>?) {
        if (menuDtoList == null) return
        this.list = menuDtoList
        this.notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView

        init {
            tvTitle = view.findViewById<View>(R.id.tvTitle) as TextView
        }

        fun handler(dto: MenuDto?, position: Int) {
            if (dto == null) return

            

            tvTitle.text = dto.title
            tvTitle.setOnClickListener { callBack?.onMenuSelect(dto) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_row_adapter, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dto = list!![position]
        holder.handler(dto, position)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}
