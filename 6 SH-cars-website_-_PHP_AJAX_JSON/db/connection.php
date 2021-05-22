<?php

function OpenConnection(): mysqli
{
    $dbhost = "127.0.0.1"; //"127.0.0.1";
    $dbusername = "root";
    $dbpassword = "";
    $dbname = "sh_cars";

    $con = mysqli_connect($dbhost, $dbusername, $dbpassword, $dbname);
    if(!$con){
        die('Could not connect to DB');
    }
    return $con;
}

function CloseConnection(mysqli $con)
{
    $con->close();
}