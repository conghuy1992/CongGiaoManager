package com.conghuy.conggiaomanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.common.*
import com.conghuy.conggiaomanager.controller.login.LoginFragment
import com.conghuy.conggiaomanager.model.ResponseStatusDto

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        displayHomeAsUpEnabled(false)
        setContentView(R.layout.login_layout)

//        setTitle(Utils.getMsg(context!!, R.string.login))

        Utils.addFragment(supportFragmentManager,"",R.id.container,LoginFragment())
    }

    override fun onClick(v: View?) {
    }

    fun initView() {

    }
    fun setTitleFragment(msg:String){
        setTitle(msg)
    }
    fun back(){
        super.onBackPressed()
    }

}
