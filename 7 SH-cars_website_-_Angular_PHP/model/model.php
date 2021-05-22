<?php

require_once '../repo/DBUtils.php';
require_once 'entity/Car.php';

class Model {
    private $db;

    public function __construct() {
        $this->db = new DBUtils ();
    }

    public function getCar($carId): Car
    {
        $resultset = $this->db->selectCar($carId);
        //var_dump($resultset);
        return new Car($resultset[0]['id'],
                       $resultset[0]['model'],
                       $resultset[0]['hp'],
                       $resultset[0]['fuel'],
                       $resultset[0]['price'],
                       $resultset[0]['color'],
                       $resultset[0]['age']);
    }

    public function getAllCars(): array
    {
        $resultset = $this->db->selectAllCars();
        $cars = array();
        foreach($resultset as $key=>$val) {
            $var = new Car($val['id'],
                $val['model'],
                $val['hp'],
                $val['fuel'],
                $val['price'],
                $val['color'],
                $val['age']);
            array_push($cars, $var);
        }
        return $cars;
    }

    public function getCarsForFuel($fuel): array
    {
        $resultset = $this->db->selectCarsByFuel($fuel);
        $cars = array();
        foreach($resultset as $key=>$val) {
            $var = new Car($val['id'],
                $val['model'],
                $val['hp'],
                $val['fuel'],
                $val['price'],
                $val['color'],
                $val['age']);
            array_push($cars, $var);
        }
        return $cars;
    }

    public function getCarFuelTypes(): array
    {
        $fuelsSet = $this->db->selectTypesOfFuel();
        $fuels = array();
        foreach($fuelsSet as $g) {
            array_push ($fuels, $g["fuel"]);
        }
        return $fuels;
    }

    public function addCar($id, $model, $hp, $fuel, $price, $color, $age) {
        return $this->db->addCar(null, $model, (int)$hp, $fuel, (double)$price, $color, (int)$age);
    }

    public function updateCar($id, $model, $hp, $fuel, $price, $color, $age) {
        return $this->db->updateCar((int)$id, $model, (int)$hp, $fuel, (double)$price, $color, (int)$age);
    }

    public function removeCar($carId) {
        return $this->db->removeCar((int)$carId);
    }

}
