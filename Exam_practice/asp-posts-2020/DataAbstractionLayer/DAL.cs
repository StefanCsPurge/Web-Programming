using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;
using System.Diagnostics;

namespace asp_app.DataAbstractionLayer
{
    public class DAL
    {
        public MySqlConnection getConnection()
        {
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=;database=phplab;";
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

        public string findTopicId(string topicName)
        {
            String id = null;
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select id from topics where topicname='" + topicName + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                if (myreader.Read())
                {
                    id = myreader.GetString("id");
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException e)
            {
                Debug.Write(e);
            }
            return id;
        }

        public void addTopic(string topicName)
        {
            MySqlConnection conn = getConnection();
            conn.Open();
            try
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "INSERT INTO topics(topicname) VALUES ('" + topicName + "')";
                cmd.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
            }
            conn.Close();
        }

        public void updatePost(String postId, String username, String topicId, String text, String cdate)
        {
            MySqlConnection conn = getConnection();
            conn.Open();
            try
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "UPDATE posts SET user='" + username +
                "', topicid='" + topicId +
                "', text='" + text +
                "', date='" + cdate + "' WHERE id='" + postId + "'";
                cmd.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
            }
            conn.Close();
        }

        public void addPost(String username, String topicId, String text, String cdate)
        {
            MySqlConnection conn = getConnection();
            conn.Open();
            try
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "INSERT INTO posts(user, topicid, text, date) " +
                "VALUES ('" + username + "','" + topicId + "','" + text + "','" + cdate + "')";
                cmd.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                Debug.Write(ex.Message);
            }
            conn.Close();
        }

    }
}