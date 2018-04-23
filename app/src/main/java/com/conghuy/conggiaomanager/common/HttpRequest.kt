package com.conghuy.conggiaomanager.common

import android.content.Context
import android.util.Log
import com.android.volley.*

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.controller.listChurch.ChurchCallBack
import com.conghuy.conggiaomanager.model.ChurchDto
import com.conghuy.conggiaomanager.model.ProvinceDto
import com.conghuy.conggiaomanager.model.ResponseStatusDto
import com.google.gson.Gson


class HttpRequest {
    private val TAG = "HttpRequest"

    fun getListAddress(context: Context, params: Map<String, String>, callBack: ProvinceCallBack, url: String) {
//        Log.d(TAG, "url:" + url)
//        Log.d(TAG, "param:" + Gson().toJson(params))
        val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    var response = response
                    if (response == null) response = ""
                    Log.d(TAG, "get response:" + response)
                    var obj: ProvinceDto? = null
                    if (Utils.isResponseObject(response)) {
                        try {
                            obj = Gson().fromJson<ProvinceDto>(response, ProvinceDto::class.java!!)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    if (obj == null) {
                        obj = ProvinceDto()
                        obj!!.http_status = Statics.HTTP_FAIL
                        obj!!.message = Utils.getMsg(context, R.string.can_not_parse_response)
                    }
                    if (obj!!.http_status == Statics.HTTP_SUCCESS
                            && obj!!.list != null
                            && obj!!.list!!.isNotEmpty())
                        callBack.onSuccess(obj.list)
                    else
                        callBack.onFail()
                },
                Response.ErrorListener { error ->
                    Log.d(TAG, "onErrorResponse:" + error.toString())
                    callBack.onVolleyError(error)
                }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
                //Adding parameters to request
//                params["datework"] = datework

                //returning parameter
                return params
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(
                Statics.TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        //Adding the string request to the queue
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }

    fun getResponse(context: Context, params: Map<String, String>, callBack: ResponseStatusCallBack, url: String) {
        Log.d(TAG, "url:" + url)
        Log.d(TAG, "param:" + Gson().toJson(params))
        val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    var response = response
                    if (response == null) response = ""
                    Log.d(TAG, "insert_province response:" + response)
                    var obj: ResponseStatusDto? = null
                    if (Utils.isResponseObject(response)) {
                        try {
                            obj = Gson().fromJson<ResponseStatusDto>(response, ResponseStatusDto::class.java!!)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    if (obj == null) {
                        obj = ResponseStatusDto()
                        obj!!.http_status = Statics.HTTP_FAIL
                        obj!!.message = Utils.getMsg(context, R.string.can_not_parse_response)
                    }
                    if (obj!!.http_status == Statics.HTTP_SUCCESS)
                        callBack.onSuccess(obj)
                    else
                        callBack.onFail(obj)
                },
                Response.ErrorListener { error ->
                    Log.d(TAG, "onErrorResponse:" + error.toString())
                    callBack.onVolleyError(error)
                }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
                //Adding parameters to request
//                params["datework"] = datework

                //returning parameter
                return params
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(
                Statics.TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        //Adding the string request to the queue
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }

    fun get_church(context: Context, params: Map<String, String>, callBack: ChurchCallBack) {
        Log.d(TAG, "get_church: ${Gson().toJson(params)}")
        val stringRequest = object : StringRequest(Request.Method.POST, Statics.get_church,
                Response.Listener { response ->
                    var response = response
                    if (response == null) response = ""
                    Log.d(TAG, "get_church response:$response")
                    var obj: ChurchDto? = null
                    if (Utils.isResponseObject(response)) {
                        try {
                            obj = Gson().fromJson<ChurchDto>(response, ChurchDto::class.java!!)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    if (obj == null) {
                        obj = ChurchDto()
                        obj!!.http_status = Statics.HTTP_FAIL
                        obj!!.message = Utils.getMsg(context, R.string.can_not_parse_response)
                    }
                    if (obj!!.http_status == Statics.HTTP_SUCCESS
                            && obj.list != null && obj.list!!.isNotEmpty()) {
//                        obj!!.list?.add(0, ChurchDto(0, Statics.Church_title_details))
//                        obj!!.list?.add(0, ChurchDto(0, Statics.Church_header_details))

                        // for display all address
//                        for (i in 0 until obj.list!!.size) {
//                            obj.list!![i].type = Statics.Church_details
////                            obj.list!![i].isShowTitle = true
//                            if (i > 0) {
//                                if ((obj.list!![i].id_province == obj.list!![i - 1].id_province)
//                                        && (obj.list!![i].church_name.equals(obj.list!![i - 1].church_name))
//                                        && (obj.list!![i].id_districts == obj.list!![i - 1].id_districts)
//                                        && (obj.list!![i].id_ward == obj.list!![i - 1].id_ward)) {
//                                    obj.list!![i].isShowTitle = false
//                                }
//                            }
//                        }

                        var list = obj.list
                        var i: Int = 0
                        while (i < list!!.size) {
                            list!![i].type = Statics.Church_details_group
                            if (list[i].listChild == null) list[i].listChild = ArrayList<ChurchDto>()

                            var dto = Utils.cloneChurch(list[i])
                            list[i].listChild?.add(dto)

                            if (i > 0) {
                                if ((list[i].id_province == list[i - 1].id_province)
                                        && (list[i].id_districts == list[i - 1].id_districts)
                                        && (list[i].id_ward == list[i - 1].id_ward)
                                        && (list[i].church_name.equals(list[i - 1].church_name))) {
                                    list[i - 1].listChild?.add(list[i])
                                    list.removeAt(i)
                                    i--
                                }
                            }
                            i++
                        }

//                        for(i in 0 until list.size){
//                            Log.d(TAG,"name:"+list[i].church_name)
//                            Utils.longInfo("list convert", Gson().toJson(list[i]))
//                        }



                        callBack.onSuccess(obj.list)
                    } else
                        callBack.onFail()
//                    timeDtoList.add(ChurchDto(0, Statics.Church_title))
//                    timeDtoList.add(ChurchDto(0, Statics.Church_header))
                },
                Response.ErrorListener { error ->
                    Log.d(TAG, "onErrorResponse:" + error.toString())
                    callBack.onVolleyError(error)
                }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
                //Adding parameters to request
//                params["datework"] = datework

                //returning parameter
                return params
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(
                Statics.TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        //Adding the string request to the queue
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}
