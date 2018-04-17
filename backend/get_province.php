<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/common/db_connect.php';
require_once __DIR__ . '/common/db_config.php';

// connecting to db
$db = new DB_CONNECT();

//echo "SELECT * FROM ".PROVINCE_TABLE. " ORDER BY name ASC";

$result = mysql_query("SELECT * FROM ".PROVINCE_TABLE. " ORDER BY name ASC") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    $response[common_list] = array();    
    while ($row = mysql_fetch_array($result)) {        
        $product = array();
        $product["id"] = $row["id"];
        $product["name"] = $row["name"];
        $product["status"] = $row["status"];
        $product["create_time"] = $row["create_time"];
        // push single product into final response array
        array_push($response[common_list], $product);
    }
    // http_status
    $response[common_http_status] = 1;
	$response[common_message] = "success";
    // echoing JSON response
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
} else {
    // no result found
    $response[common_http_status] = 0;
    $response[common_message] = "No province found";

    // echo no users JSON
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
}
?>
