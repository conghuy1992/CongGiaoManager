package com.conghuy.conggiaomanager.controller.listChurch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.VolleyError

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.HttpRequest
import com.conghuy.conggiaomanager.common.PrefManager
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.controller.insertChurch.InsertChurchAdapter
import com.conghuy.conggiaomanager.model.ChurchDto
import com.conghuy.conggiaomanager.model.ProvinceDto
import com.conghuy.conggiaomanager.view.province.AddressSelectCallBack
import com.conghuy.conggiaomanager.view.province.AddressView

/**
 * Created by Android on 4/11/2018.
 */

class ChurchFragment : Fragment(), AddressSelectCallBack {
    var TAG = "ChurchFragment"
    var layoutProvince: FrameLayout? = null
    var provinceView: AddressView? = null
    var recyclerView: RecyclerView? = null
    var adapter: InsertChurchAdapter? = null
    var progressBar: ProgressBar? = null
    var tvNodata: TextView? = null
    var tvName: TextView? = null
    var tvAddress: TextView? = null
    var v_2: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.church_fragment, container, false)
        initView(v)
        return v
    }

    override fun startProcess(url: String?) {
        showLoading(false)
        showTextNodata(false)
    }

    override fun onSelected(obj: ProvinceDto) {
        val params = HashMap<String, String>()
        params["user_id"] = "" + PrefManager(activity!!).getUserId()
        params["id_province"] = "" + obj.id
        params["type"] = "" + Statics.Church_filter_province
        get_church(params)
    }

    fun initView(v: View) {

        adapter = InsertChurchAdapter(context!!, ArrayList<ChurchDto>())
        recyclerView = v.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter

        progressBar = v.findViewById<View>(R.id.progressBar) as ProgressBar
        tvNodata = v.findViewById<View>(R.id.tvNodata) as TextView


        v_2 = v.findViewById<View>(R.id.v_2) as View
        tvName = v_2?.findViewById<View>(R.id.tvName) as TextView
        tvAddress = v_2?.findViewById<View>(R.id.tvAddress) as TextView

        tvAddress?.visibility = View.GONE
        tvName?.text = Utils.getMsg(activity!!, R.string.address)
        showTextNodata(false)
        showLoading(false)

        layoutProvince = v.findViewById<View>(R.id.layoutProvince) as FrameLayout
        provinceView = AddressView(context!!, this, Statics.get_province, 0)
        layoutProvince?.addView(provinceView)

    }

    fun showLoading(flag: Boolean) {
        progressBar?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun showTextNodata(flag: Boolean) {
        tvNodata?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun showList(flag: Boolean) {
//        recyclerView?.visibility = if (flag) View.VISIBLE else View.GONE
    }

    fun get_church(params: HashMap<String, String>) {
        showLoading(true)
        showTextNodata(false)
        showList(false)
        adapter?.update(ArrayList<ChurchDto>())
        HttpRequest().get_church(activity!!, params, object : ChurchCallBack {
            override fun onSuccess(list: List<ChurchDto>?) {
//                Utils.showMsg(activity, "onSuccess")
                showTextNodata(false)
                showLoading(false)

                adapter?.update(list as MutableList)
                showList(true)
            }

            override fun onFail() {
                showLoading(false)
                showTextNodata(true)
            }

            override fun onVolleyError(error: VolleyError) {
                Utils.onVolleyError(activity!!, error)
                showLoading(false)
                showTextNodata(true)
            }
        })
    }
}

