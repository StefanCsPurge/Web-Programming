using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using SHCarsASP_API.Data_Abstraction_Layer;

namespace SHCarsASP_API.Controllers
{
    public class AuthenticationController : ApiController
    {
        // POST: api/Authentication/Login
        public bool Login([FromBody] dynamic value)
        {
            string username = value.user.Value;
            string pwd = value.pwd.Value;
            DAL dal = new DAL();
            return dal.login(username,pwd);
        }
    }
}
