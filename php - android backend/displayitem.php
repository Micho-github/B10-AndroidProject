<?php
require "conn.php";

// Check if the item ID is received via GET request
if (isset($_GET['Item_id'])) {
    $Item_id = $_GET['Item_id'];

    // Use prepared statements to prevent SQL injection
    $stmt = $conn->prepare("SELECT item.Item_ID, item.Item_Name, item.Item_Description, item.Price, item.Item_Image, user.Username, user.Email, user.Phone_No
                            FROM item 
                            INNER JOIN user ON item.User_id = user.User_id 
                            WHERE item.Item_ID = ?");
    $stmt->bind_param("i", $Item_id);
    $stmt->execute();

    // Check for errors after executing the query
    if (!$stmt) {
        $response = array('error' => 'Query failed: ' . $conn->error);
        echo json_encode($response);
        exit();
    }

    $result = $stmt->get_result();

    // Check if there are results
    if ($result->num_rows > 0) {
        // Fetch data and store in an associative array
        $row = $result->fetch_assoc();
        $item = array(
            'Item_ID' => $row['Item_ID'],
            'Item_Name' => $row['Item_Name'],
            'Item_Description' => $row['Item_Description'],
            'Price' => $row['Price'],
            'Item_Image' => base64_encode($row['Item_Image']), // Encode binary image data as base64
            'Username' => $row['Username'],
            'Email' => $row['Email'],
            'Phone_No' => $row['Phone_No']
        );

        // Return JSON response
        echo json_encode($item);
    } else {
        // Return a JSON response indicating that the item was not found
        echo json_encode(array('error' => 'Item not found'));
    }

    // Close prepared statement
    $stmt->close();
} else {
    // Return a JSON response indicating that Item_id is not provided
    echo json_encode(array('error' => 'Item ID not provided'));
}

// Close connection
$conn->close();
?>
