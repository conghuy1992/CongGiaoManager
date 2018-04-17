package com.conghuy.conggiaomanager.view.province

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.HttpRequest
import com.conghuy.conggiaomanager.common.ProvinceCallBack
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ProvinceDto


class AddressView : LinearLayout, View.OnClickListener {
    val TAG = "AddressView"
    var spinner: Spinner? = null
    var tvNodata: TextView? = null
    var btnReload: Button? = null
    var progressBar: ProgressBar? = null
    var con: Context? = null
    var callBack: AddressSelectCallBack? = null
    var url: String? = null
    var id_address: Int = 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, callBack: AddressSelectCallBack, url: String, id_address: Int) : super(context) {
        this.callBack = callBack
        con = context
        this.url = url
        this.id_address = id_address
        init(context)
    }

    private fun init(context: Context) {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(R.layout.spinner_layout, this, true)
        spinner = findViewById<View>(R.id.spinner) as Spinner
        btnReload = findViewById<View>(R.id.btnReload) as Button
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        tvNodata = findViewById<View>(R.id.tvNodata) as TextView

        btnReload?.setOnClickListener(this)
        showTextNodata(false)
        showSpinner(false)
        showBtnReload(false)
        showLoading(false)

        if (url.equals(Statics.get_province)) {
            callApi()
        }
    }

    override fun onClick(v: View?) {
        if (v == btnReload) {
            callApi()
        }
    }

    fun getObject(): ProvinceDto? {
        if (index == -1) return null
        return list!![index]
    }

    fun resetData() {
        if (list != null && list!!.isNotEmpty() && adapter != null) {
            list?.clear()
            adapter?.notifyDataSetChanged()
        }
    }


    fun initAdapter() {
        adapter = ArrayAdapter<ProvinceDto>(con,
                android.R.layout.simple_spinner_item, list)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapter

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d(TAG, "position: " + position)
                index = position
                var obj = list!![index]
                obj.url = url
                callBack?.onSelected(obj)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    var adapter: ArrayAdapter<ProvinceDto>? = null
    var list: MutableList<ProvinceDto>? = null
    var index = -1
    fun updateIdAddress(id: Int) {
        id_address = id
    }

    fun callApi() {
        callBack?.startProcess(url)
        index = -1
        showTextNodata(false)
        showSpinner(false)
        showBtnReload(false)
        showLoading(true)
        val params = HashMap<String, String>()
        if (url.equals(Statics.get_districts)) {
            params["id_province"] = "" + id_address
        } else if (url.equals(Statics.get_ward)) {
            params["id_districts"] = "" + id_address
        }
        HttpRequest().getListAddress(con!!, params, object : ProvinceCallBack {
            override fun onSuccess(l: List<ProvinceDto>?) {
                list = l as MutableList
                showLoading(false)
                initAdapter()
                showSpinner(true)
            }

            override fun onFail() {
                showTextNodata(true)
                Utils.showMsg(con, "No data")
                showLoading(false)
            }

            override fun onVolleyError(error: VolleyError) {
                showBtnReload(true)
                showLoading(false)
                Utils.onVolleyError(con!!, error)
            }

        }, url!!)
    }

    fun showTextNodata(flag: Boolean) {
        tvNodata?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun showSpinner(flag: Boolean) {
        spinner?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun showLoading(flag: Boolean) {
        progressBar?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun showBtnReload(flag: Boolean) {
        btnReload?.visibility = if (flag) View.VISIBLE else View.GONE
    }
}
