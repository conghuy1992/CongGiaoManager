package com.conghuy.conggiaomanager.common

import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.model.ProvinceDto

interface ProvinceCallBack{
    fun onSuccess(list: List<ProvinceDto>?)
    fun onFail()
    fun onVolleyError(error: VolleyError)
}
