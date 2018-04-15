<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

$result = mysql_query("SELECT * FROM nhatho_province") or die(mysql_error());
$key = "list";
$message = "message";
$http_status="http_status";

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    $response[$key] = array();    
    while ($row = mysql_fetch_array($result)) {        
        $product = array();
        $product["id"] = $row["id"];
        $product["name"] = $row["name"];
        $product["status"] = $row["status"];
        $product["create_time"] = $row["create_time"];
        // push single product into final response array
        array_push($response[$key], $product);
    }
    // http_status
    $response[$http_status] = 1;
	$response[$message] = "success";
    // echoing JSON response
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
} else {
    // no result found
    $response[$http_status] = 0;
    $response[$message] = "No $key found";

    // echo no users JSON
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
}
?>
