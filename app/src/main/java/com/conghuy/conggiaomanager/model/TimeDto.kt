package com.conghuy.conggiaomanager.model

class TimeDto {
    var timeOne: Long = 0
    var timeTwo: Long = 0
    var timeThree: Long = 0
    var timeFour: Long = 0
    var type: Int = 0

    constructor(timeMillisecond: Long, type: Int) {
        this.type = type
    }
}
