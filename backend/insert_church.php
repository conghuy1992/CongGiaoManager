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

if (isset($_POST['name']) 
	&& isset($_POST["user_id"])
	&& isset($_POST['status']) 
	&& isset($_POST['id_districts']) 
	&& isset($_POST['id_province']) 
	&& isset($_POST['id_ward']) ) {
    
    $name         = $_POST['name'];
    $status       = $_POST['status'];
    $id_districts = $_POST['id_districts'];
    $id_province  = $_POST['id_province'];
    $id_ward      = $_POST['id_ward'];    
    $user_id = $_POST['user_id'];
    // connecting to db
    $db = new DB_CONNECT();
    
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO " . CHURCH_TABLE .
	"(name, status, id_districts, id_province, id_ward, user_id) 
	VALUES('$name', '$status','$id_districts','$id_province','$id_ward', '$user_id')");
    $last_id = mysql_insert_id();
    // check if row inserted or not
    if ($result) {		
        // successfully inserted into database
		$response["last_id"] = $last_id;
        $response[common_http_status] = 1;	
        $response[common_message]     = "successfully created.";           
        // echoing JSON response
        echo json_encode($response);     
    } else {
        // failed to insert row
        $response[common_http_status] = 0;
        $response[common_message]     = "Oops! An error occurred.";        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response[common_http_status] = 0;
    $response[common_message]     = "Required field(s) is missing";
    
    // echoing JSON response
    echo json_encode($response);
}
?>