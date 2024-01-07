<?php
require "conn.php";
$item_image = $_POST["itemimage"];
$item_name = $_POST["itemname"];
$item_description = $_POST["itemdescription"];
$price = $_POST["itemprice"];
$user_id = $_POST["userid"];
$category_code = $_POST["categorycode"];
$subcategory_code = $_POST["subcategorycode"];
$city_code = $_POST["citycode"];

$mysql_qry ="insert into item (Item_Image,Item_Name,Item_Description,Price,User_id,Category_Code,Sub_Category_Code,City_Code) values ('
$item_image','$item_name','$item_description','$price','$user_id','$category_code','$subcategory_code','$city_code')";

if($conn->query($mysql_qry) === TRUE){
    echo "Insert Successfull";
}else{
    echo "Error: " . $mysql_qry . "<br>" / $conn->error;
}
$conn->close();
?>
