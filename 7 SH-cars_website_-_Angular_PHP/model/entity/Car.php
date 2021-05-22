<?php

class Car implements JsonSerializable
{
    public $id;
    public $model;
    public $hp;
    public $fuel;
    public $price;
    public $color;
    public $age;

    /**
     * Car constructor.
     * @param $id
     * @param $model
     * @param $hp
     * @param $fuel
     * @param $price
     * @param $color
     * @param $age
     */
    public function __construct($id, $model, $hp, $fuel, $price, $color, $age)
    {
        $this->id = $id;
        $this->model = $model;
        $this->hp = $hp;
        $this->fuel = $fuel;
        $this->price = $price;
        $this->color = $color;
        $this->age = $age;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getModel()
    {
        return $this->model;
    }

    /**
     * @param mixed $model
     */
    public function setModel($model)
    {
        $this->model = $model;
    }

    /**
     * @return mixed
     */
    public function getHp()
    {
        return $this->hp;
    }

    /**
     * @param mixed $hp
     */
    public function setHp($hp)
    {
        $this->hp = $hp;
    }

    /**
     * @return mixed
     */
    public function getFuel()
    {
        return $this->fuel;
    }

    /**
     * @param mixed $fuel
     */
    public function setFuel($fuel)
    {
        $this->fuel = $fuel;
    }

    /**
     * @return mixed
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @param mixed $price
     */
    public function setPrice($price)
    {
        $this->price = $price;
    }

    /**
     * @return mixed
     */
    public function getColor()
    {
        return $this->color;
    }

    /**
     * @param mixed $color
     */
    public function setColor($color)
    {
        $this->color = $color;
    }

    /**
     * @return mixed
     */
    public function getAge()
    {
        return $this->age;
    }

    /**
     * @param mixed $age
     */
    public function setAge($age)
    {
        $this->age = $age;
    }

    public function jsonSerialize()
    {
        return get_object_vars($this);
    }
}
