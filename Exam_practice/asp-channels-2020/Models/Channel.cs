using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace asp_app.Models
{
    public class Channel
    {
        private int id;
        private int ownerId;
        private string name;
        private string description;
        private string subscribers;

        public int Id { get => id; set => id = value; }
        public int OwnerId { get => ownerId; set => ownerId = value; }
        public string Name { get => name; set => name = value; }
        public string Description { get => description; set => description = value; }
        public string Subscribers { get => subscribers; set => subscribers = value; }
    }
}