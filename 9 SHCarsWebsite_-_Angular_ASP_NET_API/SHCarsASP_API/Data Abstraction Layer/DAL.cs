using SHCarsASP_API.Models;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SHCarsASP_API.Data_Abstraction_Layer
{
    public class DAL
    {
        public MySqlConnection getConnection()
        {
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=;database=sh_cars;";
            return new MySqlConnection(myConnectionString);

        }

        public bool login(string user, string password)
        {

            List<String> users = new List<String>();

            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();


                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users where username='" + user + "'and password='" + password + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    users.Add(myreader.GetString("username"));
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
                return false;
            }
            return users.Count == 1;

        }

        public int GetUserIdOfUser(string user)
        {
            int report = 0;
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;

                cmd.CommandText = "select id from users where username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    report = myreader.GetInt32("id");
                }

                myreader.Close();

                conn.Close();
            }
            catch (MySqlException e)
            {
                Console.Write(e.Message);
                report = -1;
            }
            return report;
        }

        public Car getCar(int id)
        {
            Car car = new Car();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from cars where id='" + id + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                if (myreader.Read())
                {
                    car.Id = myreader.GetInt32("id");
                    car.Model = myreader.GetString("model");
                    car.Hp = myreader.GetInt32("hp");
                    car.Fuel = myreader.GetString("fuel");
                    car.Price = myreader.GetInt32("price");
                    car.Color = myreader.GetString("color");
                    car.Age = myreader.GetInt32("age");
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return car;
        }

        public List<Car> GetAllCars()
        {
            List<Car> slist = new List<Car>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from cars";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Car car = new Car();
                    car.Id = myreader.GetInt32("id");
                    car.Model = myreader.GetString("model");
                    car.Hp = myreader.GetInt32("hp");
                    car.Fuel = myreader.GetString("fuel");
                    car.Price = myreader.GetInt32("price");
                    car.Color = myreader.GetString("color");
                    car.Age = myreader.GetInt32("age");
                    slist.Add(car);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {
                Console.WriteLine("here");
                Console.Write(ex.Message);
            }
            return slist;
        }

        public List<Car> GetCarsWithFuel(string fuel)
        {
            List<Car> slist = new List<Car>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from cars where fuel='" + fuel + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Car car = new Car();
                    car.Id = myreader.GetInt32("id");
                    car.Model = myreader.GetString("model");
                    car.Hp = myreader.GetInt32("hp");
                    car.Fuel = myreader.GetString("fuel");
                    car.Price = myreader.GetInt32("price");
                    car.Color = myreader.GetString("color");
                    car.Age = myreader.GetInt32("age");
                    slist.Add(car);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message);
            }
            return slist;
        }

        public List<string> GetAllFuels()
        {
            List<string> types = new List<string>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select distinct fuel from cars";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    types.Add(myreader.GetString("fuel"));
                    System.Diagnostics.Debug.WriteLine(myreader.GetString("fuel"));
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException e)
            {
                System.Diagnostics.Debug.WriteLine(e.Message);
            }
            return types;
        }

        public bool AddCar(Car car)
        {
            MySqlCommand cmd = new MySqlCommand();
            MySqlConnection conn = getConnection();
            conn.Open();
            cmd.Connection = conn;
            cmd.CommandText = "INSERT INTO cars(model,hp,fuel,price,color,age) VALUES ('" +
                  car.Model + "', '" + car.Hp + "', '" + car.Fuel + "', '" + car.Price + "', '" + car.Color + "', '" + car.Age + "');";
            int cnt = cmd.ExecuteNonQuery();
            conn.Close();
            return cnt == 1;
        }


        public bool UpdateCar(Car car)
        {
            MySqlCommand cmd = new MySqlCommand();
            MySqlConnection conn = getConnection();
            conn.Open();
            cmd.Connection = conn;
            cmd.CommandText = "UPDATE cars SET model='" + car.Model + 
                                               "',hp='" + car.Hp + 
                                             "',fuel='" + car.Fuel + 
                                            "',price='" + car.Price + 
                                            "',color='" + car.Color + 
                                              "',age='" + car.Age + 
                                         "' WHERE id='" + car.Id + "';";
            int cnt = cmd.ExecuteNonQuery();
            conn.Close();
            return cnt == 1;
        }

        public bool DeleteCar(int id)
        {
            MySqlCommand cmd = new MySqlCommand();
            MySqlConnection conn = getConnection();
            conn.Open();
            cmd.Connection = conn;
            cmd.CommandText = "DELETE FROM cars WHERE id=" + id;
            int cnt = cmd.ExecuteNonQuery();
            conn.Close();
            return cnt == 1;
        }

    }
}