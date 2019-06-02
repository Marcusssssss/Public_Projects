<?php

require_once("Repo/DBUtils.php");

$pdo = new DBUtils("Rooms");

$result = $pdo->insert("Category, Type, Price, HId",
    "'" . $_POST['category'] . "','" . $_POST['type'] . "'," . $_POST['price'] . "," . $_POST['hid']);

if ($result > 0)
    echo "Data inserted!";
else
    echo "Data wasn't inserted! Try again!";