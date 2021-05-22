using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SHCarsASP_API.Models
{
    public class Car
    {
        private int id;
        private string model;
        private int hp;
        private string fuel;
        private int price;
        private string color;
        private int age;

        public int Id { get => id; set => id = value; }
        public string Model { get => model; set => model = value; }
        public int Hp { get => hp; set => hp = value; }
        public string Fuel { get => fuel; set => fuel = value; }
        public int Price { get => price; set => price = value; }
        public string Color { get => color; set => color = value; }
        public int Age { get => age; set => age = value; }
    }
}