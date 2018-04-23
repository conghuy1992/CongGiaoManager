package com.conghuy.conggiaomanager.controller.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.LoginActivity

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.HttpRequest
import com.conghuy.conggiaomanager.common.ResponseStatusCallBack
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ResponseStatusDto

/**
 * Created by Android on 4/11/2018.
 */

class RegisterFragment : Fragment(), View.OnClickListener {
    var btnLogin: Button? = null
    var edUn: EditText? = null
    var editPw: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.register_fragment, container, false)
        initView(v)
        return v
    }

    override fun onClick(v: View?) {
        if (v == btnLogin) {
            register()
        }
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                (activity as LoginActivity).onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun initView(v: View) {
        (activity as LoginActivity).displayHomeAsUpEnabled(true)
        (activity as LoginActivity).setTitleFragment(Utils.getMsg(context!!, R.string.register))

        editPw = v.findViewById<View>(R.id.editPw) as EditText
        edUn = v.findViewById<View>(R.id.edUn) as EditText

        btnLogin = v.findViewById<View>(R.id.btnLogin) as Button
        btnLogin?.setOnClickListener(this)

    }
    fun enableBtnInsert(flag: Boolean) {
        btnLogin?.isEnabled = flag
    }

    fun loginSuccess(obj: ResponseStatusDto?) {
        edUn!!.setText("")
        editPw!!.setText("")
        enableBtnInsert(true)
        Utils.showMsg(context!!, obj?.message)
    }
    fun register() {
        var userName = edUn?.text.toString().trim()

        if (userName.isEmpty()) {
            Utils.showMsg(context, "input username")
            return
        }

        var pw = editPw?.text.toString().trim()
        if (pw.isEmpty()) {
            Utils.showMsg(context, "input password")
            return
        }

        val params = HashMap<String, String>()
        params["name"] = userName
        params["password"] = pw

        enableBtnInsert(false)

        HttpRequest().getResponse(context!!, params, object : ResponseStatusCallBack {
            override fun onSuccess(obj: ResponseStatusDto?) {
                loginSuccess(obj)
            }

            override fun onFail(obj: ResponseStatusDto?) {
                enableBtnInsert(true)
                Utils.showMsg(context!!, obj?.message)
            }

            override fun onVolleyError(error: VolleyError) {
                enableBtnInsert(true)
                Utils.onVolleyError(context!!, error)
            }
        }, Statics.register_user)
    }
}

