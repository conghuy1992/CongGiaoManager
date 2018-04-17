package com.conghuy.conggiaomanager.controller.insertChurch

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
import com.conghuy.conggiaomanager.R.id.recyclerView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.conghuy.conggiaomanager.model.TimeDto
import com.google.gson.Gson
import java.sql.Time


/**
 * Created by Android on 4/11/2018.
 */

class AddChurchFragment : Fragment(), View.OnClickListener, AddressSelectCallBack {
    var TAG = "AddChurchFragment"
    var layoutProvince: FrameLayout? = null
    var layoutDistricts: FrameLayout? = null
    var layoutWard: FrameLayout? = null
    var provinceView: AddressView? = null
    var districtsView: AddressView? = null
    var wardView: AddressView? = null
    var btnInsert: Button? = null
    var editText: EditText? = null
    var recyclerView: RecyclerView? = null
    var adapter: InsertChurchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.add_church_fragment, container, false)
        initView(v)
        return v
    }

    override fun onClick(v: View?) {
        if (v == btnInsert) {
            insert()
        }
    }

    override fun startProcess(url: String?) {
        enableBtnInsert(false)
        if (url.equals(Statics.get_province)) {
            districtsView?.resetData()
            wardView?.resetData()
        } else if (url.equals(Statics.get_districts)) {
            wardView?.resetData()
        }
    }

    override fun onSelected(obj: ProvinceDto) {
        Log.d(TAG, "onSelected:" + obj.url)
        if (obj.url.equals(Statics.get_province)) {
            // load districts
            districtsView?.updateIdAddress(obj.id)
            districtsView?.callApi()
        } else if (obj.url.equals(Statics.get_districts)) {
            // load ward
            wardView?.updateIdAddress(obj.id)
            wardView?.callApi()
        } else if (obj.url.equals(Statics.get_ward)) {
            enableBtnInsert(true)
            // load ward
//            wardView?.updateIdAddress(obj.id)
//            wardView?.callApi()
        }
    }

    fun initView(v: View) {
        layoutProvince = v.findViewById<View>(R.id.layoutProvince) as FrameLayout
        layoutDistricts = v.findViewById<View>(R.id.layoutDistricts) as FrameLayout
        layoutWard = v.findViewById<View>(R.id.layoutWard) as FrameLayout

        editText = v.findViewById<View>(R.id.editText) as EditText

        provinceView = AddressView(context!!, this, Statics.get_province, 0)
        districtsView = AddressView(context!!, this, Statics.get_districts, 0)
        wardView = AddressView(context!!, this, Statics.get_ward, 0)

        btnInsert = v.findViewById<View>(R.id.btnInsert) as Button
        btnInsert?.setOnClickListener(this)

        layoutProvince?.addView(provinceView)
        layoutDistricts?.addView(districtsView)
        layoutWard?.addView(wardView)

        var timeDtoList = ArrayList<TimeDto>()
        timeDtoList.add(TimeDto(0, Statics.Church_title))
        timeDtoList.add(TimeDto(0, Statics.Church_header))
        timeDtoList.add(TimeDto(0, Statics.Church_plus))

        adapter = InsertChurchAdapter(context!!, timeDtoList)
        recyclerView = v.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    fun enableBtnInsert(flag: Boolean) {
        btnInsert?.isEnabled = flag
    }

    fun isHaveTime(obj: TimeDto): Boolean {
        if ((obj.type == 0)
                && (obj.timeOne != 0L
                        || obj.timeTwo != 0L
                        || obj.timeThree != 0L
                        || obj.timeFour != 0L)) {
            return true
        }
        return false
    }

    fun insert() {
        var name = editText?.text.toString().trim()
        if (name == null || name.isEmpty()) {
            Utils.showMsg(context, "input name church")
            return
        }

        if (provinceView?.getObject() == null) {
            Utils.showMsg(context, "province null")
            return
        }

        if (districtsView?.getObject() == null) {
            Utils.showMsg(context, "districts null")
            return
        }

        if (wardView?.getObject() == null) {
            Utils.showMsg(context, "ward null")
            return
        }
        var listTime = ArrayList<TimeDto>()
        var list = adapter?.getTime()
        var i: Int = 0
        while (i < list!!.size) {
            var obj = list[i]
            if (isHaveTime(obj)) {
                listTime.add(obj)
            }
            i++
        }

        if (listTime == null || listTime.size == 0) {
            Utils.showMsg(context, "select time please!")
            return
        }

        enableBtnInsert(false)
        val params = HashMap<String, String>()
        params["name"] = name
        params["id_districts"] = "" + districtsView?.getObject()?.id
        params["status"] = "" + PrefManager(activity!!).getUserType()
        params["id_province"] = "" + provinceView?.getObject()?.id
        params["id_ward"] = "" + wardView?.getObject()?.id

        HttpRequest().getResponse(activity!!, params, object : ResponseStatusCallBack {
            override fun onSuccess(obj: ResponseStatusDto?) {
//                editText?.setText("")
//                enableBtnInsert(true)
//                Utils.showMsg(activity!!, obj?.message)

                insert_time_open(obj!!.last_id,listTime)
            }

            override fun onFail(obj: ResponseStatusDto?) {
                enableBtnInsert(true)
                Utils.showMsg(activity!!, obj?.message)
            }

            override fun onVolleyError(error: VolleyError) {
                enableBtnInsert(true)
                Utils.onVolleyError(activity!!, error)
            }
        }, Statics.insert_church)
    }


    fun insert_time_open(id_church: Int, list: ArrayList<TimeDto>) {
        if (list != null && list.size > 0) {
            var obj = list[0]
            val params = HashMap<String, String>()
            params["id_church"] = "" + id_church
            params["status"] = "" + PrefManager(activity!!).getUserType()
            params["normal_day_morning"] = "" + obj.timeOne
            params["normal_day_afternoon"] = "" + obj.timeTwo
            params["special_day_morning"] = "" + obj.timeThree
            params["special_day_afternoon"] = "" + obj.timeFour

            Log.d(TAG,"insert_time_open params:"+ Gson().toJson(params))
            HttpRequest().getResponse(activity!!, params, object : ResponseStatusCallBack {
                override fun onSuccess(response: ResponseStatusDto?) {
                    // remove adapter
                    adapter?.remove(obj)

                    list.removeAt(0)
                    insert_time_open(id_church,list)
                }

                override fun onFail(obj: ResponseStatusDto?) {
                    enableBtnInsert(true)
                    Utils.showMsg(activity!!, obj?.message)
                }

                override fun onVolleyError(error: VolleyError) {
                    enableBtnInsert(true)
                    Utils.onVolleyError(activity!!, error)
                }
            }, Statics.insert_time_open)
        } else {
            // finish
            editText?.setText("")
            enableBtnInsert(true)
            Utils.showMsg(activity!!, "successfully")
        }
    }
}

