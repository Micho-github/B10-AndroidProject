<?php
require "conn.php";

$searchQuery = $_GET['query'];

// Perform a search query based on your database schema and structure
// Replace the following query with your actual search logic
$query = "SELECT * FROM items WHERE Item_Name LIKE '%$searchQuery%'";

$result = mysqli_query($connection, $query);

$response = array();

while ($row = mysqli_fetch_assoc($result)) {
    $item = array(
        'Item_id' => $row['Item_id'],
        'Item_Name' => $row['Item_Name'],
        'Price' => $row['Price'],
        'Item_Image' => base64_encode($row['Item_Image'])  // Assuming you store images as BLOB in the database
    );

    array_push($response, $item);
}

echo json_encode($response);

// Close the database connection
mysqli_close($connection);
?>