<?php
require "conn.php";

$sql = "SELECT Item_id, Item_Name, Price, Item_Image FROM Item";
$result = $conn->query($sql);

// Check if there are results
if ($result->num_rows > 0) {
    // Fetch data and store in array
    $items = array();
    while ($row = $result->fetch_assoc()) {
        $item = array(
            'Item_id' => $row['Item_id'],
            'Item_Name' => $row['Item_Name'],
            'Price' => $row['Price'],
            'Item_Image' => base64_encode($row['Item_Image'])  // Encode binary image data as base64
        );
        $items[] = $item;
    }

    // Return JSON response
    echo json_encode($items);
} else {
    echo "No items found";
}

// Close connection
$conn->close();
?>
