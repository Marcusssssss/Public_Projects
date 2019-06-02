<?php

require_once("Repo/DBUtils.php");

$pdo = new DBUtils("Reservations");

$cid = $pdo->selectOneRowQuery("select Id as cid from Clients where Name='" . $_REQUEST['cname'] . "'");


$affectedRows = $pdo->insert("CId, RId, StartDate, EndDate",
    $cid['cid'] . "," . $_REQUEST['roomid'] . ",'" . $_REQUEST['startdate'] . "','" . $_REQUEST['enddate'] . "'");

if ($affectedRows > 0)
    echo "Reservation done!";
else
    echo "Data wasn't inserted! Try again!";

