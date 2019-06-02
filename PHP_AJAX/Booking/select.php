<?php

// Made based on this tutorials:
// https://www.webslesson.info/2016/08/make-pagination-using-ajax-with-jquery-php-and-mysql.html
// https://www.webslesson.info/2016/02/live-table-add-edit-delete-using-ajax-jquery-in-php-mysql.html

session_start();

include_once("Repo/DBUtils.php");

$pdo = new DBUtils("Rooms");
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

if(isset($_SESSION['page']) && !empty($_SESSION['page']))
    $_SESSION['page'] = $page;
else $page=1;

$startFrom = ($page - 1) * $rowsPerPage;
$result = $pdo->madeUpSelectQuery("select R.Id as Id, R.Category as Category, R.Type as Type, R.Price as Price, H.Name as Hotel from Rooms R
                                        inner join Hotels H on R.HId=H.Id order by R.Id ASC LIMIT $startFrom, $rowsPerPage");

$output .= '
<div class="table-responsive" style="padding-bottom: 20px;">
        <table class="table table-bordered">
            <tr>
                <th width="15%">Room Id</th>
                <th width="25%">Category</th>
                <th width="25%">Type</th>
                <th width="15%">Price</th>
                <th width="20%">Hotel</th>
            </tr>    
';

if (count($result) > 0) {
    foreach ($result as $row) {
        $output .= '<tr>' .
            '<td>' . $row['Id'] . '</td>' .
            '<td class="category" data-id1="' . $row["Id"] . '" contenteditable="'.$editable.'">' . $row['Category'] . '</td>' .
            '<td class="type" data-id2="' . $row["Id"] . '" contenteditable="'.$editable.'">' . $row['Type'] . '</td>' .
            '<td class="price" data-id3="' . $row["Id"] . '" contenteditable="'.$editable.'">' . $row['Price'] . '</td>' .
            '<td class="hotel" data-id4="' . $row["Id"] . '">' . $row['Hotel'] . '</td>';
        if ($delete == 'true')
            $output .= '<td> <button name="btn_delete" class="btn btn-xs btn-danger btn_delete" data-id5="' . $row['Id'] . '"> X </button> </td>' .
                '</tr>';
    }
    if($add=='true')
        $output .= '<tr>
                  <td></td>
                  <td id="category" contenteditable></td>
                  <td id="type" contenteditable></td>
                  <td id="price" contenteditable></td>
                  <td id="hotel" contenteditable></td>
                  <td><button name="btn_add" id="btn_add" class="btn btn-xs btn-success">+</button></td>
                  </tr>
                 ';
} else {
    $output .= '<tr>
                <td colspan=\'4\'>Data not Found </td>
              </tr>';
}

$output .= '</table> <br/> <div align="center">';
$result = $pdo->personalizedSelect("order by Id ASC");
$totalRecords = count($result);
$totalPages = ceil($totalRecords / $rowsPerPage);
for ($i = 1; $i <= $totalPages; $i++) {
    $output .= "<span class='pagination_link' style='cursor:pointer; padding:10px; border: 1px solid #ccc;' id='" . $i . "'>" . $i . "</span>";
}

$output .= '</div> </div> <br/> </br> <br/>';

echo $output;

