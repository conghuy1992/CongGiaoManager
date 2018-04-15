package com.conghuy.conggiaomanager

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.conghuy.conggiaomanager.common.Statics
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.model.ProvinceDto
import com.conghuy.conggiaomanager.view.province.AddressSelectCallBack
import com.conghuy.conggiaomanager.view.province.AddressView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AddressSelectCallBack {
    var TAG = "MainActivity"
    var context: Context? = null
    var layoutProvince: FrameLayout? = null
    var layoutDistricts: FrameLayout? = null
    var layoutWard: FrameLayout? = null
    var provinceView: AddressView? = null
    var districtsView: AddressView? = null
    var wardView: AddressView? = null
    var btnInsert: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initView()

    }

    override fun onClick(v: View?) {
        if (v == btnInsert) {
            insert()
        }
    }

    override fun onSelected(obj: ProvinceDto) {
        Log.d(TAG,"onSelected:"+obj.url)
        if (obj.url.equals(Statics.get_province)) {
            // load districts
            districtsView?.updateIdAddress(obj.id)
            districtsView?.callApi()
        } else if (obj.url.equals(Statics.get_districts)) {
            // load ward
            wardView?.updateIdAddress(obj.id)
            wardView?.callApi()
        }
    }

    fun initView() {
        layoutProvince = findViewById<View>(R.id.layoutProvince) as FrameLayout
        layoutDistricts = findViewById<View>(R.id.layoutDistricts) as FrameLayout
        layoutWard = findViewById<View>(R.id.layoutWard) as FrameLayout

        provinceView = AddressView(context!!, this, Statics.get_province, 0)
        districtsView = AddressView(context!!, this, Statics.get_districts, 0)
        wardView = AddressView(context!!, this, Statics.get_ward, 0)

        btnInsert = findViewById<View>(R.id.btnInsert) as Button
        btnInsert?.setOnClickListener(this)

        layoutProvince?.addView(provinceView)
        layoutDistricts?.addView(districtsView)
        layoutWard?.addView(wardView)
    }

    fun insert() {
        if (provinceView?.getObject() == null) Utils.showMsg(context, "province null")

        if (districtsView?.getObject() == null) Utils.showMsg(context, "districts null")
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
