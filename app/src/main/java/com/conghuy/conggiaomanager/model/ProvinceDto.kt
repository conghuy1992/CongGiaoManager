package com.conghuy.conggiaomanager.model

class ProvinceDto {
    var http_status: Int = 0
    var message: String? = null
    var list: List<ProvinceDto>? = null

    var id: Int = 0
    var name: String? = null
    var status: Int = 0
    var create_time: String? = null
    var id_province:Int =0
    var id_districts:Int=0

    // check common
    var url: String? = null

    override fun toString(): String {
        return if (name == null) "" else name!!
    }
}
