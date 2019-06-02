<?php

include_once("Repo/DBUtils.php");

session_start();

if ($_REQUEST['admin'] != 1) {
    header("Location: Booking.php");
} else {
    echo "<script type='text/javascript'>alert('You are logged in as admin! \\n ' +
            'All the changes that you will make, will be reflected in the database!');</script>";
}

try {
    $pdo = new DBUtils("hotels");
} catch (PDOException $ex) {
    echo 'Cannot connect to database!';
    die;
}

?>

<!DOCTYPE html>
<html>
<head>
    <link href="bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
    <link href="css/addroom.css" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 container">
            <br/>
            <h2>Existing rooms</h2>
            <br/>
            <div id="live_data">

            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {
        function fetch_data(page) {
            $.ajax({
                url: "select.php",
                method: "POST",
                data: {
                    page: page,
                    add: 'yes',
                    delete: 'yes',
                    editable: 'yes'
                },
                success: function (data) {
                    $('#live_data').html(data);
                }
            });
        }

        fetch_data(1);

        $(document).on('click', '.pagination_link', function () {
            var page = $(this).attr('id');
            fetch_data(page);
        });

        function isInteger(value) {
            return /^\d+$/.test(value);
        }


        $(document).on('click', '#btn_add', function () {
            var category = $('#category').text();
            var type = $('#type').text();
            var price = $('#price').text();
            var hid = $('#hotel').text();
            if (category == "") {
                alert("Enter Category!");
                return false;
            }
            if (type == "") {
                alert("Enter Type!");
                return false;
            }
            if (price == "" || !isInteger(price)) {
                alert("Enter a valid Price!");
                return false;
            }
            if (hid == "" || !isInteger(hid)) {
                alert("Enter a valid HotelId!");
                return false;
            }
            $.ajax({
                url: "insert1.php",
                method: "POST",
                data: {
                    category: category,
                    type: type,
                    price: price,
                    hid: hid
                },
                dataType: "text",
                success: function (data) {
                    alert(data);
                    fetch_data();
                }
            });
        });

        function edit_data(id, text, column_name) {
            $.ajax({
                url: "edit.php",
                method: "POST",
                data: {
                    id: id,
                    text: text,
                    column_name: column_name
                },
                dataType: "text",
                success: function (data) {
                    //alert(data);
                }
            });
        }

        $(document).on('blur', '.category', function () {
            var id = $(this).data("id1");
            var category = $(this).text();
            edit_data(id, category, "category");
        });

        $(document).on('blur', '.type', function () {
            var id = $(this).data("id2");
            var type = $(this).text();
            edit_data(id, type, "type");
        });

        $(document).on('blur', '.price', function () {
            var id = $(this).data("id3");
            var price = $(this).text();
            edit_data(id, price, "price");
        });

        $(document).on('click', '.btn_delete', function () {
            var id = $(this).data("id5");
            if (confirm("Are you sure you want to delete this?")) {
                $.ajax({
                    url: "delete.php",
                    method: "POST",
                    data: {id: id},
                    dataType: "text",
                    success: function (data) {
                        alert(data);
                        fetch_data();
                    }
                });
            }
        });
    });

</script>

</body>

</html>
