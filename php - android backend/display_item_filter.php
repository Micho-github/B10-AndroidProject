<?php
require "conn.php";

// Get parameters from the request
$categoryCode = $_GET['category_code'];
$subcategoryCode = $_GET['subcategory_code'];
$cityCode = $_GET['city_code'];

// Fetch filtered item data
$sql = "SELECT Item_id, Item_Name, Price, Item_Image FROM Item WHERE Category_Code = $categoryCode AND Sub_Category_Code = $subcategoryCode AND City_Code = $cityCode";
$result = $conn->query($sql);

// Prepare JSON response
$response = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $item = array(
            'Item_id' => $row['Item_id'],
            'Item_Name' => $row['Item_Name'],
            'Price' => $row['Price'],
            'Item_Image' => base64_encode($row['Item_Image']) // Convert binary image data to base64
        );
        $response[] = $item;
    }
}

// Send JSON response
header('Content-Type: application/json');
echo json_encode($response);

$conn->close();
?>