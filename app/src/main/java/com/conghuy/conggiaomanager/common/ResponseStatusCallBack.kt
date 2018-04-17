package com.conghuy.conggiaomanager.common

import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.model.ResponseStatusDto

interface ResponseStatusCallBack{
    fun onSuccess(obj: ResponseStatusDto?)
    fun onFail(obj: ResponseStatusDto?)
    fun onVolleyError(error: VolleyError)
}
