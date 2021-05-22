<?php
include ('db/connection.php');

$con = OpenConnection();
if(!$con) die("No DB connection");

$sql = "SELECT DISTINCT fuel FROM cars";
$result_set = $con->query($sql);
$rows = array();
while ($row = mysqli_fetch_array($result_set, MYSQLI_NUM)) {
    //$rows[] = $row[0];
    array_push($rows, $row[0]);
}
CloseConnection($con);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cars browsing</title>
    <script src="jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<button type="button" onclick="location.href='./index.html'" style="margin-left: 20px">Home</button>
<br>

<section class="browsing" style="float: left; display: inline; padding: 20px">
    <label>
        <select name="Severity Filter" onchange="get_filtered_fuel()">
            <option selected>choose fuel</option>
            <?php
            foreach ($rows as $row) {
                echo "<option>$row</option>";
            }
            ?>
        </select>
    </label>
    <br><br>
    <table>
        <thead>
        <th>ID</th>
        <th>Model</th>
        <th>HP</th>
        <th>Fuel</th>
        <th>Price</th>
        <th>Color</th>
        <th>Age</th>
        </thead>
        <tbody>
        </tbody>
    </table>
    <p> </p>
    <br>
</section>

</body>
</html>
