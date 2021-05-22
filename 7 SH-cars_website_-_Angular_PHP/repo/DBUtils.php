<?php

class DBUtils {
    private $host = '127.0.0.1';
    private $db   = 'sh_cars';
    private $user = 'root';
    private $pass = '';
    private $charset = 'utf8';

    private $pdo;
    private $error;

    public function __construct () {
        $dsn = "mysql:host=$this->host;dbname=$this->db;charset=$this->charset";
        $opt = array(PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES   => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
        } // Catch any errors
        catch(PDOException $e){
            $this->error = $e->getMessage();
            echo "Error connecting to DB: " . $this->error;
        }
    }

    public function selectCar($id): array
    {
        $stmt = $this->pdo->query("SELECT * FROM cars where id='" . $id ."'");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectAllCars(): array
    {
        $stmt = $this->pdo->query("SELECT * FROM cars");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectTypesOfFuel(): array
    {
        $stmt = $this->pdo->query("SELECT DISTINCT fuel FROM cars");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectCarsByFuel($fuel): array
    {
        $stmt = $this->pdo->query("SELECT * FROM cars where fuel='" . $fuel ."'");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function addCar($id ,$model, $hp, $fuel, $price, $color, $age) {
        return $this->pdo->exec("INSERT INTO cars VALUES('$id','$model','$hp','$fuel','$price','$color','$age')");
    }

    public function updateCar($id, $model, $hp, $fuel, $price, $color, $age) {
        return $this->pdo->exec(
            "UPDATE cars SET model='$model',hp='$hp',fuel='$fuel',price='$price',color='$color',age='$age' 
                       WHERE id='$id'");
    }

    public function removeCar($carId) {
        return $this->pdo->exec("DELETE FROM cars WHERE id='$carId'");
    }


    private function select($table) {
        $stmt = $this->pdo->query("SELECT * FROM " . $table);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    private function insert($id, $value) {
        return $this->pdo->exec("INSERT into table values(" . $id . ",'" . $value ."');");
    }

    private function delete ($id) {
        return $this->pdo->exec("DELETE from table where id=" . $id);
    }

    private function update ($id, $value) {
        $affected_rows = $this->pdo->exec("UPDATE table SET field='" . $value ."' where id=" . $id);
    }
}
