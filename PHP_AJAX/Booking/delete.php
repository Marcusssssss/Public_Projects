<?php

require_once ("Repo/DBUtils.php");
$pdo=new DBUtils("Rooms");
if($pdo->deleteById($_POST['id'])>0)
    echo "Data deleted";
