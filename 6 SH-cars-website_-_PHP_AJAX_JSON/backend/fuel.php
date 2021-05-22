<?php
include ('../db/connection.php');
include ('../db/Car.php');
try{
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $con = OpenConnection();
        //$contents = file_get_contents('php://input');
        $fuel = $_POST['fuel'];
        if($fuel !== false && $fuel !== "choose fuel"){
            $sql = sprintf("SELECT * FROM cars WHERE fuel = '%s'", $fuel);
        }
        else{
            $sql = "SELECT * FROM cars";
        }

        $result_set = $con->query($sql);
        $rows = array();
        while ($row = mysqli_fetch_array($result_set)) {
            $rows[] = new Car($row['id'],
                                $row['model'],
                                $row['hp'],
                                $row['fuel'],
                                $row['price'],
                                $row['color'],
                                $row['age']);

        }
        header('HTTP/1.1 200 OK');
        echo json_encode($rows);
        CloseConnection($con);
        exit;
    }
} catch (Exception $e) {
    header('HTTP/1.1 500 INTERNAL_SERVER_ERROR');
    exit;
}