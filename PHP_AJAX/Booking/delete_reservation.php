<?php

require_once ("Repo/DBUtils.php");
$pdo=new DBUtils("Reservations");
if($pdo->deleteById($_POST['id'])>0)
    echo "Reservation canceled!";
