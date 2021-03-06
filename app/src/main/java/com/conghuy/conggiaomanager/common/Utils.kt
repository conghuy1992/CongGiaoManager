package com.conghuy.conggiaomanager.common

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.*
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.android.volley.VolleyError
import com.conghuy.conggiaomanager.R


import java.text.SimpleDateFormat
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.text.DecimalFormat
import java.util.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.conghuy.conggiaomanager.model.ChurchDto


/**
 * Created by android on 06/01/2018.
 */

object Utils {
    var TAG: String = "Utils";
    fun getMsg(context: Context, id: Int): String {
        return context.resources.getString(id)
    }

    fun showMsg(context: Context?, msg: String?) {
        if (context != null) {
            try {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showMsg(context: Context?, id: Int) {
        if (context != null) {
            try {
                Toast.makeText(context, getMsg(context, id), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDimenInPx(context: Context, id: Int): Int {
        return context.resources.getDimension(id).toInt()
    }

    fun formatTime(date: Long, defaultPattern: String): String {
        val simpleDateFormat = SimpleDateFormat(defaultPattern, Locale.getDefault())
        return simpleDateFormat.format(Date(date))
    }

    fun isResponseObject(response: String): Boolean {
        if (response.startsWith("\ufeff"))
            return true
        return if (response.startsWith("{")) true else false
    }

    fun getColor(context: Context, color: Int): Int {
        return context.resources.getColor(color)
    }

    fun getPrice(value: String?): String {
        if (value == null) return "0 " + Statics.UNIT
        try {
            return "" + formatAMT(java.lang.Long.parseLong(value)) + " " + Statics.UNIT
        } catch (e: Exception) {
            return "0 " + Statics.UNIT
        }
    }

    fun onVolleyError(context: Context, error: VolleyError) {
        if (context == null || error == null) return

        showMsg(context, error.toString())
    }

    fun formatAMT(value: Long): String {
        val pattern = "###,###"
        var s = ""
        val myFormatter = DecimalFormat(pattern)
        s = myFormatter.format(value) + ""
        s = s.replace(".", ",")
        return s
    }

    fun getFacebookData(`object`: JSONObject): Bundle? {
        //        Log.d(TAG,"getFacebookData:"+object.toString());
        val bundle = Bundle()
        try {
            var id = ""
            if (`object`.has("id"))
                id = `object`.optString("id")

            bundle.putString("idFacebook", id)
            if (`object`.has("first_name"))
                bundle.putString("first_name", `object`.getString("first_name"))
            if (`object`.has("last_name"))
                bundle.putString("last_name", `object`.getString("last_name"))
            if (`object`.has("email"))
                bundle.putString("email", `object`.getString("email"))
            if (`object`.has("gender"))
                bundle.putString("gender", `object`.getString("gender"))
            if (`object`.has("birthday"))
                bundle.putString("birthday", `object`.getString("birthday"))
            if (`object`.has("location"))
                bundle.putString("location", `object`.getJSONObject("location").getString("name"))
            if (`object`.has("age_range"))
                bundle.putString("age_range", `object`.getString("age_range"))

            try {
                val profile_pic = URL("https://graph.facebook.com/$id/picture?width=200&height=150")
                Log.d("profile_pic", profile_pic.toString() + "")
                bundle.putString("profile_pic", profile_pic.toString())

                if (`object`.has("cover")) {
                    //                URL cover = new URL("http://graph.facebook.com/" + id + "?fields=cover");
                    var cover_photo: String? = `object`.getJSONObject("cover").getString("source")
                    if (cover_photo == null) cover_photo = ""
                    bundle.putString("cover_photo", cover_photo)
                }

            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return null
            }

        } catch (e: JSONException) {
            Log.e(TAG, "Error parsing JSON")
        }

        return bundle
    }


    fun popup(context: Context, title: String, msg: String) {
        var build = AlertDialog.Builder(context)
        build.setTitle(title)
        build.setMessage(msg)
        build.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }

        })
        build.create().show()
    }

    fun addFragment(fragmentManager: FragmentManager, addToBackStack: String?,
                    resId: Int, fragment: android.support.v4.app.Fragment) {
        val transaction = fragmentManager.beginTransaction()
        if (addToBackStack != null && addToBackStack.trim { it <= ' ' }.isNotEmpty())
            transaction.addToBackStack(addToBackStack)
        transaction.replace(resId, fragment)
        transaction.commit()
    }

    fun showDateDialog(context: Context, calendar: Calendar, callback: DateCallback) {
        val dpd = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    callback.onFinish(cal)
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        dpd.show()
    }

    fun showTimeDialog(context: Context, calendar: Calendar, callback: DateCallback) {
        val timePickerDialog = TimePickerDialog(context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)
                    callback.onFinish(cal)
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
        timePickerDialog.show()
    }

    fun getTime(context: Context, time: Long): String {
        if (time > 0) return Utils.formatTime(time, Statics.DATE_FORMAT_HH_MM)
        else return Utils.getMsg(context, R.string.dont_select)
    }

    fun getTimeWithOutEmptyText(time: Long): String {
        if (time > 0) return Utils.formatTime(time, Statics.DATE_FORMAT_HH_MM)
        else return ""
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun longInfo(TAG: String, str: String) {
        if (str.length > 4000) {
            Log.d(TAG, str.substring(0, 4000))
            longInfo(TAG, str.substring(4000))
        } else
            Log.d(TAG, str)
    }

    fun cloneChurch(obj: ChurchDto): ChurchDto {
        var dto = ChurchDto()
        dto.church_name = obj.church_name
        dto.church_id = obj.church_id
        dto.id_province = obj.id_province
        dto.province_name = obj.province_name
        dto.id_districts = obj.id_districts
        dto.districts_name = obj.districts_name
        dto.id_ward = obj.id_ward
        dto.ward_name = obj.ward_name
        dto.open_time_id = obj.open_time_id
        dto.normal_day_morning = obj.normal_day_morning
        dto.normal_day_afternoon = obj.normal_day_afternoon
        dto.special_day_morning = obj.special_day_morning
        dto.special_day_afternoon = obj.special_day_afternoon
        return dto
    }
}
