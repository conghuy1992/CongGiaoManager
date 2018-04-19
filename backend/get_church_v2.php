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

	$query = "SELECT church.id as church_id, church.name as church_name,
			church.id_province as id_province, province.name as province_name, 
			church.id_districts as id_districts, districts.name as districts_name,
			church.id_ward as id_ward, ward.name as ward_name,
			open_time.id as open_time_id,
			open_time.normal_day_morning as normal_day_morning,
			open_time.normal_day_afternoon as normal_day_afternoon,
			open_time.special_day_morning as special_day_morning,
			open_time.special_day_afternoon as special_day_afternoon
			FROM nhatho_list_church as church, nhatho_province as province,
			nhatho_ward as ward,nhatho_districts as districts,
			nhatho_open_time as open_time
			WHERE church.id_province = province.id AND church.id_ward = ward.id AND church.id_districts = districts.id AND church.id = open_time.id_church";
			
			$query .= " AND province.id = 6";
			$query .=" ORDER BY church_name ASC";

//var_dump($query);

$result = mysql_query($query) or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    $response[common_list] = array();    
    while ($row = mysql_fetch_array($result)) {        
        $product = array();
        $product["church_id"] = $row["church_id"];
        $product["church_name"] = $row["church_name"];
        $product["id_province"] = $row["id_province"];
        $product["province_name"] = $row["province_name"];		
		$product["id_districts"] = $row["id_districts"];
		$product["districts_name"] = $row["districts_name"];
		$product["id_ward"] = $row["id_ward"];
		$product["ward_name"] = $row["ward_name"];
		$product["open_time_id"] = $row["open_time_id"];
		$product["normal_day_morning"] = $row["normal_day_morning"];
		$product["normal_day_afternoon"] = $row["normal_day_afternoon"];
		$product["special_day_morning"] = $row["special_day_morning"];		
		$product["special_day_afternoon"] = $row["special_day_afternoon"];
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
