using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;
using System.Diagnostics;
using asp_app.Models;

namespace asp_app.DataAbstractionLayer
{
    public class DAL
    {
        public MySqlConnection getConnection()
        {
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=;database=some_users;";
            return new MySqlConnection(myConnectionString);
        }

        public bool login(string user)
        {
            List<String> users = new List<String>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from persons where name='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                while (myreader.Read())
                {
                    users.Add(myreader.GetString("name"));
                }
                conn.Close();
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
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
                cmd.CommandText = "select id from persons where name='" + user + "'";
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
                Debug.Write(e);
            }
            return report;
        }

        public List<Channel> GetPersonChannels(int pid)
        {
            List<Channel> channels = new List<Channel>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from channels WHERE ownerid='" + pid + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                while (myreader.Read())
                {
                    Channel c = new Channel();
                    c.Id = myreader.GetInt32("id");
                    c.OwnerId = myreader.GetInt32("ownerid");
                    c.Name = myreader.GetString("name");
                    c.Description = myreader.GetString("description");
                    c.Subscribers = myreader.GetString("subscribers");
                    channels.Add(c);
                    //int result = Int32.Parse(input);
                }
                conn.Close();
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
            }
            return channels;
        }

        public List<Channel> GetSubscriberChannels(int sid)
        {
            List<Channel> channels = new List<Channel>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from channels";
                MySqlDataReader myreader = cmd.ExecuteReader();
                while (myreader.Read())
                {
                    String participantsStr = myreader.GetString("subscribers");
                    String[] strIds = participantsStr.Split(',');
                    foreach (string subsTuple in strIds)
                    {
                        String[] sTuple = subsTuple.Split(':');
                        if (sTuple.Length > 0 && Int32.Parse(sTuple[0]) == sid)
                        {
                            Channel c = new Channel();
                            c.Id = myreader.GetInt32("id");
                            c.Name = myreader.GetString("name");
                            c.Description = myreader.GetString("description");
                            channels.Add(c);
                            break;
                        }

                    }
                    //int result = Int32.Parse(input);
                }
                conn.Close();
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
            }
            return channels;
        }

        public string SubscribeToChannel(int userId, string channelName)
        {
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select subscribers from channels where name='" + channelName + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                string newSubs = "";
                string result = "Subscription successful";

                if (myreader.Read())
                {
                    String participantsStr = myreader.GetString("subscribers");
                    String[] strIds = participantsStr.Split(',');
                    Boolean found = false;

                    foreach (string subsTuple in strIds)
                    {
                        String[] sTuple = subsTuple.Split(':');
                        if(sTuple.Length == 2)
                        {
                            if (Int32.Parse(sTuple[0]) == userId)
                            {
                                found = true;
                                sTuple[1] = DateTime.Now.ToString("yyyy-MM-dd");
                            }
                            newSubs += sTuple[0] + ":" + sTuple[1] + ",";
                        }
                    }
                    if (!found)
                        newSubs += userId + ":" + DateTime.Now.ToString("yyyy-MM-dd");
                    myreader.Close();
                    cmd.CommandText = "UPDATE channels SET subscribers='" + newSubs + "' WHERE name='" + channelName + "'";
                    cmd.ExecuteNonQuery();
                    conn.Close();
                }
                else
                {
                    result = "Subscription failed";
                }
                return result;
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
                return "Subscription failed";
            }
            
        }
    }
}