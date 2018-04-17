package com.conghuy.conggiaomanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.conghuy.conggiaomanager.common.Utils
import com.conghuy.conggiaomanager.controller.insertChurch.AddChurchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.conghuy.conggiaomanager.controller.insertDistricts.AddDistrictsFragment
import com.conghuy.conggiaomanager.controller.insertProvince.AddProvinceFragment
import com.conghuy.conggiaomanager.controller.insertWard.AddWardFragment
import com.conghuy.conggiaomanager.controller.leftMenu.LeftMenuAdapter
import com.conghuy.conggiaomanager.controller.leftMenu.MenuCallBack
import com.conghuy.conggiaomanager.controller.leftMenu.MenuDto
import com.conghuy.conggiaomanager.controller.leftMenu.MenuEnum


class MainActivity : AppCompatActivity(),
        View.OnClickListener, MenuCallBack {
    var TAG = "MainActivity"
    var context: Context? = null
    var recyclerView: RecyclerView? = null
    var adapter: LeftMenuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        initView()

        addFragment(AddChurchFragment())

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onMenuSelect(obj: MenuDto) {
        handlerMenuSelect(obj)
    }

    override fun onClick(v: View?) {
    }

    fun addFragment(fm: Fragment) {
        Utils.addFragment(supportFragmentManager, "", R.id.container, fm)
    }

    fun initView() {
        var list = ArrayList<MenuDto>()
        list.add(MenuDto(MenuEnum.ADD_CHURCH, Utils.getMsg(context!!, R.string.add_church)))
        list.add(MenuDto(MenuEnum.ADD_PROVINCE, Utils.getMsg(context!!, R.string.add_province)))
        list.add(MenuDto(MenuEnum.ADD_DISTRICTS, Utils.getMsg(context!!, R.string.add_districts)))
        list.add(MenuDto(MenuEnum.ADD_WARD, Utils.getMsg(context!!, R.string.add_ward)))

        adapter = LeftMenuAdapter(context!!, list!!, this)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    fun handlerMenuSelect(obj: MenuDto) {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        when (obj.anEnum) {
            MenuEnum.ADD_CHURCH -> addFragment(AddChurchFragment())
            MenuEnum.ADD_PROVINCE -> addFragment(AddProvinceFragment())
            MenuEnum.ADD_DISTRICTS -> addFragment(AddDistrictsFragment())
            MenuEnum.ADD_WARD ->  addFragment(AddWardFragment())
        }
    }
}
