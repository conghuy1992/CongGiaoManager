package com.conghuy.conggiaomanager.controller.insertProvince

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.android.volley.VolleyError

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.*
import com.conghuy.conggiaomanager.model.ResponseStatusDto

/**
 * Created by Android on 4/11/2018.
 */

class AddProvinceFragment : Fragment(), View.OnClickListener {
    var edProvince: EditText? = null
    var btnAdd: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.add_province_fragment, container, false)
        initView(v)
        return v
    }

    override fun onClick(v: View?) {
        if (v == btnAdd) {
            add()
        }
    }

    fun initView(v: View) {
        edProvince = v.findViewById<View>(R.id.edProvince) as EditText
        btnAdd = v.findViewById<View>(R.id.btnAdd) as Button

        btnAdd?.setOnClickListener(this)
    }

    fun enableBtnAdd(flag: Boolean) {
        btnAdd?.isEnabled = flag
    }

    fun add() {
        var name = edProvince?.text.toString().trim()

        if (name.isEmpty()) return

        enableBtnAdd(false)
        val params = HashMap<String, String>()
        params["user_id"] = "" + PrefManager(activity!!)
        params["name"] = name
        params["status"] = ""+PrefManager(activity!!).getUserType()

        HttpRequest().getResponse(activity!!, params, object : ResponseStatusCallBack {
            override fun onSuccess(obj: ResponseStatusDto?) {
                edProvince?.setText("")
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
        }, Statics.insert_province)
    }
}

