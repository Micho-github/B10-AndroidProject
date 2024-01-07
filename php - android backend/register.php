<?php
require "conn.php";
$first_name = $_POST["first_name"];
$last_name = $_POST["last_name"];
$username = $_POST["username"];
$email = $_POST["email"];
$password = $_POST["password"];
$phone_no = $_POST["phone_no"];

$mysql_qry ="insert into user (First_Name,Last_Name,Username,Email,Password,Phone_No) values ('
$first_name','$last_name','$username','$email','$password','$phone_no')";

if($conn->query($mysql_qry) === TRUE){
    echo "Insert Successfull";
}else{
    echo "Error: " . $mysql_qry . "<br>" / $conn->error;
}
$conn->close();
?>
