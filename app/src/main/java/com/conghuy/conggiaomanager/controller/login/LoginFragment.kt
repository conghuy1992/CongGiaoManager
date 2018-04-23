package com.conghuy.conggiaomanager.controller.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.LoginActivity
import com.conghuy.conggiaomanager.MainActivity

import com.conghuy.conggiaomanager.R
import com.conghuy.conggiaomanager.common.HttpRequest
import com.conghuy.conggiaomanager.common.ResponseStatusCallBack
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ResponseStatusDto

/**
 * Created by Android on 4/11/2018.
 */

class LoginFragment : Fragment(), View.OnClickListener {
    var btnLogin: Button? = null
    var edUn: EditText? = null
    var editPw: EditText? = null
    var tvRegister: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.login_fragment, container, false)
        initView(v)
        return v
    }

    override fun onClick(v: View?) {
        if (v == btnLogin) {
            login()
        } else if (v == tvRegister) {
            register()
        }
    }

    fun initView(v: View) {
        (activity as LoginActivity).setTitleFragment(Utils.getMsg(context!!, R.string.login))

        editPw = v.findViewById<View>(R.id.editPw) as EditText
        edUn = v.findViewById<View>(R.id.edUn) as EditText

        btnLogin = v.findViewById<View>(R.id.btnLogin) as Button
        btnLogin?.setOnClickListener(this)

        tvRegister = v.findViewById<View>(R.id.tvRegister) as TextView
        tvRegister?.text = Html.fromHtml(Utils.getMsg(context!!, R.string.register))
        tvRegister?.setOnClickListener(this)
    }

    fun enableBtnInsert(flag: Boolean) {
        btnLogin?.isEnabled = flag
    }

    fun register() {
        Utils.addFragment(fragmentManager!!, "RegisterFragment", R.id.container, RegisterFragment())
    }

    fun loginSuccess(obj: ResponseStatusDto?) {
        edUn!!.setText("")
        editPw!!.setText("")
        enableBtnInsert(true)
        startActivity(Intent(activity, MainActivity::class.java))
        Utils.showMsg(context!!, obj?.message)
        activity?.finish()
    }

    fun login() {
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
        }, Statics.login)
    }
}

