package com.conghuy.conggiaomanager.controller.insertWard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.android.volley.VolleyError

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.*
import com.conghuy.conggiaomanager.model.ProvinceDto
import com.conghuy.conggiaomanager.model.ResponseStatusDto
import com.conghuy.conggiaomanager.view.province.AddressSelectCallBack
import com.conghuy.conggiaomanager.view.province.AddressView

/**
 * Created by Android on 4/11/2018.
 */

class AddWardFragment : Fragment(), AddressSelectCallBack, View.OnClickListener {
    var TAG = "AddWardFragment"
    var layoutProvince: FrameLayout? = null
    var layoutDistricts: FrameLayout? = null
    var provinceView: AddressView? = null
    var districtsView: AddressView? = null
    var edDistricts: EditText? = null
    var btnAdd: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.add_ward_fragment, container, false)
        initView(v)
        return v
    }

    override fun onClick(v: View?) {
        if (v == btnAdd) {
            add()
        }
    }

    override fun startProcess(url: String?) {
        enableBtnAdd(false)
    }

    override fun onSelected(obj: ProvinceDto) {
        Log.d(TAG, "onSelected:" + obj.url)
        if (obj.url.equals(Statics.get_province)) {
            // load districts
            districtsView?.updateIdAddress(obj.id)
            districtsView?.callApi()
        } else if (obj.url.equals(Statics.get_districts)) {
            enableBtnAdd(true)
            this.obj = obj
            // load ward
//            wardView?.updateIdAddress(obj.id)
//            wardView?.callApi()
        }
    }

    var obj: ProvinceDto? = null
    fun initView(v: View) {
        layoutProvince = v.findViewById<View>(R.id.layoutProvince) as FrameLayout
        provinceView = AddressView(context!!, this, Statics.get_province, 0)
        layoutProvince?.addView(provinceView)

        layoutDistricts = v.findViewById<View>(R.id.layoutDistricts) as FrameLayout
        districtsView = AddressView(context!!, this, Statics.get_districts, 0)
        layoutDistricts?.addView(districtsView)

        edDistricts = v.findViewById<View>(R.id.edDistricts) as EditText
        btnAdd = v.findViewById<View>(R.id.btnAdd) as Button
        btnAdd?.setOnClickListener(this)
    }

    fun enableBtnAdd(flag: Boolean) {
        btnAdd?.isEnabled = flag
    }

    fun add() {
        if (provinceView?.getObject() == null) return
        if (districtsView?.getObject() == null) return

        var name = edDistricts?.text.toString().trim()

        if (name.isEmpty()) return

        var id_districts = districtsView?.getObject()!!.id

        enableBtnAdd(false)
        val params = HashMap<String, String>()
        params["name"] = name
        params["id_districts"] = "" + id_districts
        params["status"] = "" + PrefManager(activity!!).getUserType()

        HttpRequest().getResponse(activity!!, params, object : ResponseStatusCallBack {
            override fun onSuccess(obj: ResponseStatusDto?) {
                edDistricts?.setText("")
                enableBtnAdd(true)
                Utils.showMsg(activity!!, obj?.message)
            }

            override fun onFail(obj: ResponseStatusDto?) {
                enableBtnAdd(true)
                Utils.showMsg(activity!!, obj?.message)
            }

            override fun onVolleyError(error: VolleyError) {
                enableBtnAdd(true)
                Utils.onVolleyError(activity!!, error)
            }
        }, Statics.insert_ward)
    }
}

