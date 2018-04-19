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

if (isset($_POST['id_church'])
	&&isset($_POST["user_id"]) 
	&& isset($_POST['status']) 
	&& isset($_POST['normal_day_morning']) 
	&& isset($_POST['normal_day_afternoon']) 
	&& isset($_POST['special_day_morning']) 
	&& isset($_POST['special_day_afternoon']) 
	) 
	{    
    $id_church         = $_POST['id_church'];
    $status       = $_POST['status'];
    $normal_day_morning = $_POST['normal_day_morning'];
    $normal_day_afternoon  = $_POST['normal_day_afternoon'];
    $special_day_morning      = $_POST['special_day_morning'];    
    $special_day_afternoon      = $_POST['special_day_afternoon'];    
	$user_id = $_POST['user_id'];
    // connecting to db
    $db = new DB_CONNECT();
        
   // insert time
    $result  = mysql_query("INSERT INTO " . CHURCH_OPEN_TIME_TABLE . 
	"(id_church, normal_day_morning, normal_day_afternoon, special_day_morning, special_day_afternoon,status, user_id) 
    VALUES('$id_church', '$normal_day_morning','$normal_day_afternoon','$special_day_morning','$special_day_afternoon','$status', '$user_id')");
    
    // check if row inserted or not
    if ($result) {		
        // successfully inserted into database
		
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