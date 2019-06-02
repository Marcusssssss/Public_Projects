<?php

require_once("Repo/DBUtils.php");

$pdo = new DBUtils("Rooms");
$id = $_REQUEST['id'];
$text = $_REQUEST['text'];
$column_name = $_REQUEST['column_name'];

$result = 0;

if (is_numeric($text) && !is_float($text))
    $result = $pdo->update($column_name, $text, $id);
else if (is_string($text))
    $result = $pdo->update($column_name, "'" . $text . "'", $id);

if ($result > 0)
    echo "Data updated!";