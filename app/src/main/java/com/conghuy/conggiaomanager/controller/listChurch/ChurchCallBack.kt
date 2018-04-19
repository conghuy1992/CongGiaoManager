package com.conghuy.conggiaomanager.controller.listChurch

import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.model.ChurchDto

interface ChurchCallBack{
    fun onSuccess(list: List<ChurchDto>?)
    fun onFail()
    fun onVolleyError(error: VolleyError)
}