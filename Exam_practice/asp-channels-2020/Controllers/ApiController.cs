using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using asp_app.DataAbstractionLayer;
using asp_app.Models;
using System.Diagnostics;

namespace asp_app.Controllers
{
    public class ApiController : Controller
    {
        // GET: Api
        public ActionResult Index()
        {
            return View("MainPage");
        }

        public string Test()
        {
            return "It's working";
        }

        public string GetPersonChannels()
        {
            string name = Request.Params["name"];
            DAL dal = new DAL();
            int person_id = dal.GetUserIdOfUser(name);

            List<Channel> slist = dal.GetPersonChannels(person_id);
            //ViewData["studentList"] = slist;

            string result = "<table border=1><thead><th>ID</th><th>OwnerID</th><th>Name</th><th>Description</th><th>Subscribers</th></thead>";

            foreach (Channel c in slist)
            {
                result += "<tr><td>" + c.Id + "</td><td>" + c.OwnerId + "</td><td>" + c.Name + "</td><td>" + c.Description + "</td><td>" + c.Subscribers + "</td></tr>";
            }

            result += "</table>";
            return result;
        }

        public string GetSubscriptions()
        {
            int user_id = Int32.Parse(Request.Params["userid"]);
            string result = "<table border=1><thead><th>Name</th><th>Description</th></thead>";
            DAL dal = new DAL();
            List<Channel> slist = dal.GetSubscriberChannels(user_id);
            foreach (Channel c in slist)
            {
                result += "<tr><td>" + c.Name + "</td><td>" + c.Description + "</td></tr>";
            }
            result += "</table>";
            return result;
        }

        public string SubscribeToChannel()
        {
            int user_id = Int32.Parse(Request.Params["userid"]);
            string channelName = Request.Params["channel"];
            DAL dal = new DAL();
            string result = dal.SubscribeToChannel(user_id,channelName);
            return result;
        }
    }
}