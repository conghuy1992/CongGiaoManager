package com.conghuy.conggiaomanager.common

import android.content.Context
import android.util.Log
import com.android.volley.*

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.conghuy.conggiaomanager.R
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
    fun getResponse(context: Context, params: Map<String, String>, callBack: ResponseStatusCallBack,url:String) {
        Log.d(TAG, "url:" + url)
        Log.d(TAG, "param:" + Gson().toJson(params))
        val stringRequest = object : StringRequest(Request.Method.POST,url,
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

}
