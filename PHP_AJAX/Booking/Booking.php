<?php

require_once ("Repo/DBUtils.php");

$pdo=new DBUtils("Hotels");

$minMax=$pdo->selectOneRowQuery("select min(Id) as min, max(Id) as max from Rooms");

$minRoomId=$minMax['min'];
$maxRoomId=$minMax['max'];

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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
        } );
    </script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 container">
            <br/>
            <h2>Make your reservation</h2>
            <br/>
            <form action="insert.php" method="post" id="<?php echo $_REQUEST['ClientName']; ?>">
                <div class="row">
                    <div class="form-group col-md-8">
                        <label>Hotel:</label>
                        <select name="hotel" id="hotel">
                            <?php
                            $result = $pdo->selectColumnsWhere(" id, name ", "");
                            foreach ($result as $row)
                                echo "<option value='" . $row['id'] . "'>" . $row['name'] . "</option>";
                            ?>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="roomid">Room Id:</label>
                        <input type="number" class="form-control" id="roomid" placeholder="Choose an existing Room Id" name="roomid"
                               min="<?php echo $minRoomId; ?>" max="<?php echo $maxRoomId; ?>">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="startdate">From:</label>
                        <input type="text" class="form-control datepicker" id="startdate" placeholder="Start Date" name="startdate">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="enddate">Until:</label>
                        <input type="text" class="form-control datepicker" id="enddate" placeholder="End Date" name="enddate">
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="form-group col-md-4">
                        <button type="submit" class="btn btn-outline-dark">Submit</button>
                    </div>
                </div>
            </form>

            <p id="resultP" class="alert-info" style="font-size: 15px;">

            </p>

        </div>
        <div class="col-md-6 container">
            <br/>
            <h2>Existing rooms</h2>
            <br/>
            <div id="live_data">

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 container">
            <br/>
            <h2>Your Reservations</h2>
            <br/>
            <div id="your_reservations">

            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {

        function empty_fields(){
            $('#roomid').val("");
            $('#startdate').val("");
            $('#enddate').val("");
        }

        $("form").submit(function (e) {
            e.preventDefault();
            var cname=$("form").attr('id');
            var hid = $('#hotel').val();
            var roomid = $('#roomid').val();
            var startdate = $('#startdate').val();
            var enddate = $('#enddate').val();

            if (enddate == "") {
                alert("Enter an End Date!");
                return false;
            }

            if (roomid == "") {
                alert("Enter an Room Id!");
                return false;
            }

            if (startdate == "") {
                alert("Enter a Start Date!");
                return false;
            }

            $.ajax({
                url: "insert.php",
                method: "POST",
                data: {
                    cname: cname,
                    hid: hid,
                    roomid: roomid,
                    startdate: startdate,
                    enddate: enddate
                },
                dataType: "text",
                success: function (data) {
                    alert(data);
                    empty_fields();
                    fetch_reservations();
                }
            });
        });

        function fetch_data(page) {
            $.ajax({
                url: "select.php",
                method: "POST",
                data: {
                    page: page,
                    add: 'no',
                    delete: 'no',
                    editable: 'no'
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

        function fetch_reservations(page){
            $.ajax({
                url: "select_reservations.php",
                method: "POST",
                data:{
                    cname: $("form").attr("id"),
                    page: page,
                    add: 'no',
                    delete: 'yes',
                    editable: 'no'
                },
                success: function(data){
                    $('#your_reservations').html(data);
                }
            })
        }

        fetch_reservations(1);

        $(document).on('click', '.pagination_link1', function () {
            var page = $(this).attr('id');
            fetch_reservations(page);
        });


        $(document).on('click', '.btn_delete1', function () {
            var id = $(this).data("id5");
            if (confirm("Are you sure you want to cancel this reservation?")) {
                $.ajax({
                    url: "delete_reservation.php",
                    method: "POST",
                    data: {id: id},
                    dataType: "text",
                    success: function (data) {
                        alert(data);
                        fetch_reservations();
                    }
                });
            }
        });

    });

</script>


</body>

</html>
