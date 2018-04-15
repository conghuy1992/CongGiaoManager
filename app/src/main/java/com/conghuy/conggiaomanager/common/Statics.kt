package com.conghuy.conggiaomanager.common

/**
 * Created by Android on 3/29/2018.
 */

interface Statics {
    companion object {
        val RC_SIGN_IN = 999

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
        val root_url = "http://huynhconghuy.esy.es/conggiao_app/"
        val get_province = root_url + "get_province.php"
        val get_districts = root_url + "get_districts.php"
        val get_ward = root_url + "get_ward.php"


    }
}
