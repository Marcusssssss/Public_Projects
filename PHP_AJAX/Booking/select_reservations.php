<?php


// Made based on this tutorials:
// https://www.webslesson.info/2016/08/make-pagination-using-ajax-with-jquery-php-and-mysql.html
// https://www.webslesson.info/2016/02/live-table-add-edit-delete-using-ajax-jquery-in-php-mysql.html

session_start();

include_once("Repo/DBUtils.php");

$pdo = new DBUtils("Reservations");
$rowsPerPage = 4;
$output = "";

if (isset($_POST["page"]))
    $page = $_POST["page"];
else $page = $_SESSION['page'];

$add = $delete = $editable = 'false';

if (isset($_POST['add']))
    if ($_POST['add'] == 'yes')
        $add = 'true';

if (isset($_POST['delete']))
    if ($_POST['delete'] == 'yes')
        $delete = 'true';

if (isset($_POST['editable']))
    if ($_POST['editable'] == 'yes')
        $editable = 'true';

if (isset($_SESSION['page']) && !empty($_SESSION['page']))
    $_SESSION['page'] = $page;
else $page = 1;

$startFrom = ($page - 1) * $rowsPerPage;

$result = $pdo->madeUpSelectQuery("select R.Id as 'Id', H.Name as 'Hotel', R.RId as 'Room Id', R.StartDate as 'Start Date', R.EndDate as 'End Date'  
                                        from Reservations R
                                        inner join Clients C on C.Id=R.CId 
                                        inner join Rooms RR on R.RId=RR.Id 
                                        inner join Hotels H on H.Id=RR.HId 
                                        where C.Name='".$_POST['cname']."'
                                        order by R.StartDate ASC LIMIT $startFrom, $rowsPerPage");

$output .= '
<div class="table-responsive" style="padding-bottom: 20px;">
        <table class="table table-bordered">
            <tr>
                <th width="25%">Hotel</th>
                <th width="15%">Room Id</th>
                <th width="30%">Start Date</th>
                <th width="30%">End Date</th>
            </tr>    
';

if (count($result) > 0) {
    foreach ($result as $row) {
        $output .= '<tr>' .
            '<td class="hotel" data-id1="' . $row["Id"] . '" contenteditable="' . $editable . '">' . $row['Hotel'] . '</td>' .
            '<td class="roomid" data-id2="' . $row["Id"] . '" contenteditable="' . $editable . '">' . $row['Room Id'] . '</td>' .
            '<td class="startdate" data-id3="' . $row["Id"] . '" contenteditable="' . $editable . '">' . $row['Start Date'] . '</td>' .
            '<td class="enddate" data-id4="' . $row["Id"] . '" contenteditable="' . $editable . '">' . $row['End Date'] . '</td>';
        if ($delete == 'true')
            $output .= '<td> <button name="btn_delete" class="btn btn-xs btn-danger btn_delete1" data-id5="' . $row['Id'] . '"> X </button> </td>' .
                '</tr>';
    }
    /*
    if ($add == 'true')
        $output .= '<tr>
                  <td></td>
                  <td id="Hotel" contenteditable></td>
                  <td id="Room Id" contenteditable></td>
                  <td id="Start Date" contenteditable></td>
                  <td id="End Date" contenteditable></td>
                  <td><button name="btn_add" id="btn_add" class="btn btn-xs btn-success">+</button></td>
                  </tr>
                 ';
    */
} else {
    $output .= '<tr>
                <td colspan=\'4\'>You have no reservations registered! </td>
              </tr>';
}

$output .= '</table> <br/> <div align="center">';
$result = $pdo->personalizedSelect("order by Id ASC");
$totalRecords = count($result);
$totalPages = ceil($totalRecords / $rowsPerPage);
for ($i = 1; $i <= $totalPages; $i++) {
    $output .= "<span class='pagination_link1' style='cursor:pointer; padding:10px; border: 1px solid #ccc;' id='" . $i . "'>" . $i . "</span>";
}

$output .= '</div> </div> <br/> </br> <br/>';

echo $output;

