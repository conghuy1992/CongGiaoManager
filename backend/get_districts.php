<?php
/*
 * Following code will get single message details
 * A message is identified by message id (pid)
 */
// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/common/db_connect.php';
require_once __DIR__ . '/common/db_config.php';

// connecting to db
$db = new DB_CONNECT();
$key = "list";
$message = "message";
$http_status="http_status";
// check for post data
if (isset($_POST["id_province"])&&isset($_POST["user_id"])) {    
$id_province = $_POST['id_province'];    
$user_id = $_POST['user_id'];    
$sql = "SELECT * FROM " .DISTRICTS_TABLE. 
    " WHERE id_province = '" . mysql_real_escape_string($id_province) . "'
	ORDER BY name ASC ";
    
    $result = mysql_query($sql) or die(mysql_error());
    
    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // looping through all results        
        $response[$key] = array();
        
        while ($row = mysql_fetch_array($result)) {
            // temp user array
            $product             = array();
            $product["id"]       = $row["id"];
            $product["name"]     = $row["name"];
            $product["id_province"] = $row["id_province"];
			$product["status"] = $row["status"];
			$product["create_time"] = $row["create_time"];
            // push single product into final response array
            array_push($response[$key], $product);
        }
        // success
        $response[$http_status] = 1;
        
        // echoing JSON response
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    } else {
        // no phone found
        $response[$http_status] = 0;
        $response[$message] = "No $key found";
        
        // echo no users JSON
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
    
} else {
    // required field is missing
    $response[$http_status] = 0;
    $response[$message] = "Required field(s) is missing";
    
    // echoing JSON response
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
}
?>
