<?php
require "conn.php";
$user_name = $_POST["username"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from user where Username like '$user_name' and Password like '$user_pass' ;";
$result = mysqli_query($conn , $mysql_qry);
if(mysqli_num_rows($result) > 0) {
    echo "Login successfull";
}
else{
    echo "Login not successfull";
}
?>