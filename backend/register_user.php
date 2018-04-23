<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['name']) && isset($_POST['password']) ) {
    
    $name     = $_POST['name'];
    $password = $_POST['password'];
    $email    = "";
    
    // include db connect class
    require_once __DIR__ . '/common/db_connect.php';
    require_once __DIR__ . '/common/db_config.php';
    // connecting to db
    $db = new DB_CONNECT();
    
    // get all products from products table
//    $result = mysql_query("SELECT name FROM user WHERE BINARY  name = '$name'") or die(mysql_error());
	
	
	$sql = "SELECT name FROM user WHERE BINARY  name = '".mysql_real_escape_string($name)."'";
	$result = mysql_query($sql) or die(mysql_error());
    
    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // exist user
        $response[common_http_status] = 0;
        $response[common_message] = "exist user";
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no user found -> add user
        // mysql inserting a new row
        $result = mysql_query("INSERT INTO user(name, password, email) VALUES('".mysql_real_escape_string($name)."', '".mysql_real_escape_string($password)."', '".mysql_real_escape_string($email)."')");
        
        // check if row inserted or not
        if ($result) {
            // successfully inserted into database
            $response[common_http_status] = 1;
            $response[common_message] = "User successfully created.";
            
            // echoing JSON response
            echo json_encode($response);
        } else {
            // failed to insert row
            $response[common_http_status] = 0;
            $response[common_message] = "Oops! An error occurred.";
            
            // echoing JSON response
            echo json_encode($response);
        }
    }
    
} else {
    // required field is missing
    $response[common_http_status] = 0;
    $response[common_message] = "Required field(s) is missing";
    
    // echoing JSON response
    echo json_encode($response);
}
?>