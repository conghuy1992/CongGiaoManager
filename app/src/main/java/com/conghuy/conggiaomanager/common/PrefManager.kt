package com.conghuy.conggiaomanager.common

import android.content.Context
import android.content.SharedPreferences

class PrefManager {
    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?=null
    var PREF_NAME = "church_db"
    // shared pref mode
    var PRIVATE_MODE = 0

    // userType = 1 -> admin else normal user
    var userType = "userType"
    var userId="userId"

    constructor() {}
    constructor(context: Context) {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit()
    }

    fun ClearData() {
        editor?.clear()
        editor?.commit()
    }

    fun setUserType(s: Int) {
        editor?.putInt(userType, s)
        editor?.commit()
    }

    fun getUserType(): Int {
        return pref!!.getInt(userType, 0)
    }

    fun setUserId(s: Int) {
        editor?.putInt(userId, s)
        editor?.commit()
    }

    fun getUserId(): Int {
        return pref!!.getInt(userId, 0)
    }
}
