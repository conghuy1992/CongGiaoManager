package com.conghuy.conggiaomanager.model

class ChurchDto  {
    var http_status: Int = 0
    var message: String? = null
    var list: MutableList<ChurchDto>? = null

    var church_id: Int = 0
    var church_name: String? = null
    var status:Int =0
    var id_province: Int = 0
    var province_name: String? = null
    var id_districts: Int = 0
    var districts_name: String? = null
    var id_ward: Int = 0
    var ward_name: String? = null
    var open_time_id: Int = 0
    var normal_day_morning: Long = 0
    var normal_day_afternoon: Long = 0
    var special_day_morning: Long = 0
    var special_day_afternoon: Long = 0
    var type: Int = 0
    var isShowTitle: Boolean = true
    var listChild: MutableList<ChurchDto>? = null


    constructor()
    constructor(timeMillisecond: Long, type: Int) {
        this.type = type
    }

}
