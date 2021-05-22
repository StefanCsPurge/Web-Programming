<?php
include ('../db/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $connection = OpenConnection();
    $id = $_POST['id'];
    // echo $id;
    $query = "DELETE FROM cars WHERE id='$id'";
    $connection->query($query);
    CloseConnection($connection);
}