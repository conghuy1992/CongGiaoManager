<?php
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();
// check for required fields
if (isset($_POST['name']) && isset($_POST['password'])) {
    $name     = $_POST['name'];
    $password = $_POST['password'];   
    // include db connect class    
    require_once __DIR__ . '/common/db_connect.php'; 
	require_once __DIR__ . '/common/db_config.php';	
    // connecting to db    
    $db = new DB_CONNECT();    
    // get all name from user table            
    //$result = mysql_query("SELECT * FROM user WHERE BINARY name = '$name'") or die(mysql_error());    
    $sql = "SELECT * FROM user WHERE BINARY name = '" . mysql_real_escape_string($name) . "'";    
    $result = mysql_query($sql) or die(mysql_error());             
    // check for empty result    
    if (mysql_num_rows($result) > 0) {
        $result              = mysql_fetch_array($result);
        $product             = array();
        $product["user_id"]  = $result["user_id"];
        $product["name"]     = $result["name"];
        $product["password"] = $result["password"];
        $product["email"]    = $result["email"];
        $a                   = strcmp($password, $result["password"]);
        if ($a == 0) {            
            // Login success
            $response["user_id"]  = $result["user_id"];
            $response["name"]     = $result["name"];
            $response["password"] = $result["password"];
            $response["email"]    = $result["email"];
			$response["avatar"]    = $result["avatar"];		
			$response["regId"]    = $result["regId"];			
            $response[common_http_status]  = 1;
            $response[common_message]  = "Login success";                        
        } else {            
            // Wrong password            
            $response[common_http_status] = 0;
            $response[common_message] = "Wrong password";
        }        
        // echoing JSON response        
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    } else {        
        // No user found        
        $response[common_http_status] = 0;
        $response[common_message] = "No user found";        
        // echoing JSON response        
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
} else {    
    // required field is missing    
    $response[common_http_status] = 0;
    $response[common_message] = "Required field(s) is missing";    
    // echoing JSON response    
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
}

?>