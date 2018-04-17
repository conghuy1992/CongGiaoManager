package com.conghuy.conggiaomanager.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.conghuy.conggiaomanager.R

/**
 * Created by Android on 4/11/2018.
 */

class Temp : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_main, container, false)
        initView(v)
        return v
    }

    fun initView(v: View) {}
}

