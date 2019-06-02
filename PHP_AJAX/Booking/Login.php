<?php
ob_start();
session_start();
$_SESSION['admin'] = 'nem';
require_once 'Repo/DBUtils.php';
?>

<!--
    made with https://www.tutorialspoint.com/php/php_login_example.htm
-->

<?
// error_reporting(E_ALL);
// ini_set("display_errors", 1);
?>

<html lang="en">

<head>
    <title>Booking</title>
    <link href="bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
</head>

<body>

<h2>Enter your name and email</h2>
<div class="container form-signin">

    <?php
    $msg = '';
    $db = new DBUtils("Clients");

    if (isset($_REQUEST['login']) && !empty($_REQUEST['name']) && !empty($_REQUEST['email'])) {

        if ($_REQUEST['email'] == 'admin@admin.com' || $_REQUEST['name'] == 'admin')
            header("Location: Administrate.php?admin=1");
        else {
            try {
                $results = $db->selectOneRowQuery("select Id as id from Clients where Name='" . $_REQUEST['name'] . "' and Email='" . $_REQUEST['email'] . "'");
                if (count($results) == 0)
                    $db->insertPersonalized("insert into Clients(Name, Email) values('" . $_REQUEST['name'] . "','" . $_REQUEST['email'] ."')");
                    //$db->insert("Name, Email", "'" . $_REQUEST['name'] . "', '" . $_REQUEST['email'] . "'");
                header("Location: Booking.php?ClientName=" . $_REQUEST['name']);
            } catch (PDOException $ex) {
                echo($ex->getMessage());
            }
        }
    } else $msg = 'Please, enter all the credentials!'
    ?>
</div> <!-- /container -->

<div class="container">

    <form class="form-signin" role="form" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?> " method="post">
        <h4 class="form-signin-heading"><?php echo $msg; ?></h4>
        <input type="text" class="form-control" name="name" placeholder="Name" required autofocus></br>
        <input type="email" class="form-control" name="email" placeholder="Email" required></br>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="login">Start booking</button>
    </form>

    <!--Click here to clean <a href="logout.php" title="Logout">Session.-->

</div>

</body>
</html>