<?php
// enable CORS - required for Angular UI
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
require_once '../model/model.php';
require_once '../view/view.php';

class Controller
{
    private $view;
    private $model;

    public function __construct(){
        $this->model = new Model();
        $this->view = new View();
    }

    public function service() {
        if (isset($_GET['action']) && !empty($_GET['action'])) {
            if ($_GET['action'] == "getCar")
                $this->{$_GET['action']}($_GET['carId']);
            else if ($_GET['action'] == "getAllCars")
                $this->{$_GET['action']}();
            else if ($_GET['action'] == "getFuelTypes")
                $this->{$_GET['action']}();
            else if ($_GET['action'] == "getCarsForFuel")
                $this->{$_GET['action']}($_GET['fuel']);
        }
        else if ($_SERVER['REQUEST_METHOD'] === 'DELETE'){
            $carId = $_GET['id'];
            $this->removeCar($carId);
        }
        else if ($_SERVER['REQUEST_METHOD'] === 'POST'){
                $inputJSON = file_get_contents('php://input');
                $carFields = json_decode($inputJSON, TRUE); //convert JSON into array
                $this->addCar(  $carFields['id'],
                                $carFields['model'],
                                $carFields['hp'],
                                $carFields['fuel'],
                                $carFields['price'],
                                $carFields['color'],
                                $carFields['age'] );

        }
        else if ($_SERVER['REQUEST_METHOD'] === 'PUT') {
            $putData = fopen("php://input", "r");
            $data = fread($putData, 1024);
            fclose($putData);
            $carFields = json_decode($data, TRUE); //convert JSON into array
            $this->updateCar(   $carFields['id'],
                                $carFields['model'],
                                $carFields['hp'],
                                $carFields['fuel'],
                                $carFields['price'],
                                $carFields['color'],
                                $carFields['age']);
            }
    }

    private function getCar($carId) {
        $car = $this->model->getCar($carId);
        $this->view->output($car);
    }

    private function getAllCars() {
        $cars = $this->model->getAllCars();
        $this->view->output($cars);
    }

    private function getFuelTypes(){
        $fuel = $this->model->getCarFuelTypes();
        $this->view->output($fuel);
    }

    private function getCarsForFuel($fuel){
        $cars = $this->model->getCarsForFuel($fuel);
        $this->view->output($cars);
    }

    private function addCar($id, $model, $hp, $fuel, $price, $color, $age) {
        $result = $this->model->addCar($id, $model, $hp, $fuel, $price, $color, $age);
        if ($result>0) { $r = "Success"; }
        else { $r = "Failure"; }
        $this->view->returnResult($r);
    }

    private function updateCar($id, $model, $hp, $fuel, $price, $color, $age) {
        $result = $this->model->updateCar($id, $model, $hp, $fuel, $price, $color, $age);
        if ($result>0) { $r = "Success"; }
        else { $r = "Failure"; }
        $this->view->returnResult($r);
    }

    private function removeCar($carId) {
        $result = $this->model->removeCar($carId);
        if ($result>0) { $r = "Success"; }
        else { $r = "Failure"; }
        $this->view->returnResult($r);
    }

}

$controller = new Controller();
$controller->service();
