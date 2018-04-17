package com.conghuy.conggiaomanager.view.province

import com.conghuy.conggiaomanager.model.ProvinceDto

interface AddressSelectCallBack{
    fun startProcess(url:String?)
    fun onSelected(obj:ProvinceDto)
}