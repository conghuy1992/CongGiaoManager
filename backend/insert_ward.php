<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

    // include db connect class
	require_once __DIR__ . '/common/db_connect.php';
	require_once __DIR__ . '/common/db_config.php';
	
// check for required fields

if (isset($_POST['name']) && isset($_POST['status']) && isset($_POST['id_districts'])) {    
    $name = $_POST['name'];
	$status = $_POST['status'];
	$id_districts = $_POST['id_districts'];
	
    // connecting to db
    $db = new DB_CONNECT();
	
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO ".WARD_TABLE.
	"(name, status,id_districts) VALUES('$name', '$status','$id_districts')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response[common_http_status] = 1;
        $response[common_message] = "ward successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response[common_http_status] = 0;
        $response[common_message] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response[common_http_status] = 0;
    $response[common_message] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>