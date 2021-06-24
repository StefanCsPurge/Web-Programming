using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using asp_app.DataAbstractionLayer;
using asp_app.Models;
using System.Diagnostics;
using MySql.Data.MySqlClient;

namespace asp_app.Controllers
{
    public class ApiController : Controller
    {
        private static Boolean postAdded = false;
        private static String userThatAdded = "";
        private static String addedTopicId = "";
        private static String addedText = "";
        private static String addedDate = "";

        // GET: Api
        public ActionResult Index()
        {
            return View("MainPage");
        }

        public string Test()
        {
            return "It's working";
        }

        public string GetAllPosts()
        {
            DAL dal = new DAL();
            string result = "<table border=1><thead><th>ID</th><th>User</th><th>TopicID</th><th>Text</th><th>Date</th></thead>";
            try
            {
                MySqlConnection conn = dal.getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from posts";
                MySqlDataReader myreader = cmd.ExecuteReader();
                while (myreader.Read())
                {
                    result += "<tr><td>" + myreader.GetString("id") + "</td><td>" 
                        + myreader.GetString("user") + "</td><td>" 
                        + myreader.GetString("topicid") + "</td><td>" 
                        + myreader.GetString("text") + "</td><td>" 
                        + myreader.GetString("date") + "</td></tr>";
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException e)
            {
                Debug.Write(e);
            }
            result += "</table>";
            return result;
        }

        public string CheckNotification()
        {
            string user = Request.Params["user"];
            if (postAdded && user != userThatAdded)
            {
                postAdded = false;
                return "User " + userThatAdded + " added the article ->\n" +
                        "TopicID: '" + addedTopicId + "', text: '" + addedText + "', date: '" + addedDate + "'";
            }
            return "";
        }


        public void UpdatePost()
        {
            String user = Request.Params["user"];
            String postId = Request.Params["postid"];
            String topicName = Request.Params["topicname"];
            String text = Request.Params["text"];
            String date = Request.Params["date"];
            DAL dal = new DAL();
            
            String topicId = dal.findTopicId(topicName);
            if (topicId == null)
            {
                dal.addTopic(topicName);
                topicId = dal.findTopicId(topicName);
            }
            dal.updatePost(postId, user, topicId, text, date);
        }

        public void AddPost()
        {
            String user = Request.Params["user"];
            String topicName = Request.Params["topicname"];
            String text = Request.Params["text"];
            String date = Request.Params["date"];
            DAL dal = new DAL();

            String topicId = dal.findTopicId(topicName);
            if (topicId == null)
            {
                dal.addTopic(topicName);
                topicId = dal.findTopicId(topicName);
            }
            dal.addPost(user, topicId, text, date);

            postAdded = true;
            userThatAdded = user;
            addedTopicId = topicId;
            addedText = text;
            addedDate = date;

        }
    }
}