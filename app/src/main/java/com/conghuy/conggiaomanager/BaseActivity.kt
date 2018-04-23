package com.conghuy.conggiaomanager

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.conghuy.conggiaomanager.common.Utils

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.title = Utils.getMsg(this, R.string.app_name)
        }
    }
    fun displayHomeAsUpEnabled(flag:Boolean){
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(flag)
            supportActionBar?.setDisplayShowHomeEnabled(flag)
        }
    }
     fun setTitle(msg: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = msg
        }
    }
}
