package com.conghuy.conggiaomanager.common

/**
 * Created by Android on 3/29/2018.
 */

interface Statics {
    companion object {
        val RC_SIGN_IN = 999

        //format date
        val DATE_FORMAT_YY_MM_DD_DD = "yy-MM-dd-EEEEEEE"
        val DATE_FORMAT_YYYY_MM_DD_DD = "yyyy-MM-dd-EEEEEEE"
        val DATE_FORMAT_YY_MM_DD_DD_H_M = "yy-MM-dd-EEEEEEE hh:mm aa"
        val DATE_FORMAT_YYYY_MM_DD_DD_H_M = "yyyy-MM-dd, EEEEEEE hh:mm aa"
        val DATE_FORMAT_YYYY_MM_DD_E = "yyyy-MM-dd, EEEEEEE"
        val DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
        val DATE_FORMAT_DD = "dd"
        val DATE_FORMAT_DD_CHAR = "EEEEEEE"
        val DATE_FORMAT_YY_MM_DD_H_M = "yy-MM-dd hh:mm aa"
        val DATE_TOOLBAR_FORMAT_YY_MM = "yyyy-MM"
        val DATE_TOOLBAR_FORMAT_MM_YY = "MM-yyyy"
        val DATE_TOOLBAR_FORMAT_DD = "DD"
        val DATE_FORMAT_MONTH = "MM"
        val DATE_FORMAT_YEAR = "yyyy"
        val DATE_FORMAT_MONTH_NAME = "MMM"
        val DATE_FORMAT_HH_MM_AA = "hh:mm aa"
        val DATE_FORMAT_HH_MM = "HH:mm"

        val ACTION_RECEIVER_NOTIFICATION = "ACTION_RECEIVER_NOTIFICATION"
        val ACTION_RECEIVER_CLEAR_LIST_ORDER = "ACTION_RECEIVER_CLEAR_LIST_ORDER"
        val ACTION_RECEIVER_UPDATE_TEXT_INFOR = "ACTION_RECEIVER_UPDATE_TEXT_INFOR"
        val ACTION_RECEIVER_UPDATE_TEXT_INFOR_TAB_INFOR = "ACTION_RECEIVER_UPDATE_TEXT_INFOR_TAB_INFOR"
        val ACTION_NOTIFICATION_ORDER_LIST = "ACTION_NOTIFICATION_ORDER_LIST"
        val ACTION_CLEAR_PRODUCT_FROM_LIST_ORDER = "ACTION_CLEAR_PRODUCT_FROM_LIST_ORDER"

        val product_key = "product_key"
        val product_list_key = "product_list_key"
        val account_key = "account_key"

        val TAB_ONE_1 = 1
        val TAB_ONE_2 = 2

        val UNIT = "vnđ"

        val TIMEOUT_MS = 7000

        val HTTP_SUCCESS = 1
        val HTTP_FAIL = 0
        val root_url = "http://huynhconghuy.esy.es"
        val api = root_url + "/conggiao_app/"
        val get_province = api + "get_province.php"
        val get_districts = api + "get_districts.php"
        val get_ward = api + "get_ward.php"
        val insert_province = api + "insert_province.php"
        val insert_districts = api + "insert_districts.php"
        val insert_ward = api + "insert_ward.php"
        val insert_church = api + "insert_church.php"
        val insert_time_open = api + "insert_time_open.php"
        val get_church = api + "get_church.php"
        val login = api + "login.php"
        val register_user = api + "register_user.php"



        val Church_insert_normal = 0
        val Church_header = 1
        val Church_plus = 2
        val Church_title = 3
        val Church_details = 4
        val Church_header_details = 5
        val Church_title_details = 6
        val Church_details_group = 7


        val Church_filter_province = 1
    }
}
