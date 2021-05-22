using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using SHCarsASP_API.Data_Abstraction_Layer;
using SHCarsASP_API.Models;


namespace SHCarsASP_API.Controllers
{
    public class CarsController : ApiController
    {
        // GET: api/Cars/getAll
        public List<Car> GetAll()
        {
            DAL dal = new DAL();
            List<Car> cars = dal.GetAllCars();
            return cars;
        }

        // GET: api/Cars/getAllFuels
        public List<string> GetAllFuels()
        {
            DAL dal = new DAL();
            List<string> fuels = dal.GetAllFuels();
            return fuels;
        }

        // GET: api/Cars/getAllForFuel/fuel
        public List<Car> GetAllForFuel(string id)
        {
            DAL dal = new DAL();
            List<Car> cars = dal.GetCarsWithFuel(id);
            return cars;
        }

        // GET: api/Cars/getCar/id
        public Car GetCar(int id)
        {
            DAL dal = new DAL();
            return dal.getCar(id);
        }

        // POST: api/Cars/addCar
        public void AddCar([FromBody]Car car)
        {
            System.Diagnostics.Debug.WriteLine(car.Id);
            DAL dal = new DAL();
            dal.AddCar(car);
        }

        // PUT: api/Cars/Put
        public void Put([FromBody]Car updatedCar)
        {
            System.Diagnostics.Debug.WriteLine(updatedCar.Id);
            DAL dal = new DAL();
            dal.UpdateCar(updatedCar);
        }

        // DELETE: api/Cars/Delete/5
        public void Delete(int id)
        {
            System.Diagnostics.Debug.WriteLine(id);
            DAL dal = new DAL();
            dal.DeleteCar(id);
        }
    }
}
