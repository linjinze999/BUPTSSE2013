using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StockClass
{
    //用户信息，包含用户类型、账号、密码、余额
    public class User
    {
        private int usertype;       //用户类型，0代表客户，1代表管理员
        private int usermoney;      //用户余额
        private string useraccount; //用户账号
        private string userpassword;//用户密码

        public User() //无参初始化
        {
            this.usertype = 0;
            this.usermoney = 0;
            this.useraccount = "";
            this.userpassword = "";
        }
        public User(int usertype, int usermoney, string useraccount, string userpassword) //有参初始化
        {
            this.usertype = usertype;
            this.usermoney = usermoney;
            this.useraccount = useraccount;
            this.userpassword = userpassword;
        }

        public int getUsertype()                        //得到用户类型
        {
            return this.usertype;
        }
        public void setUsertype(int usertype)           //设置用户类型
        {
            this.usertype = usertype;
        }
        public int getUsermoney()                       //得到用户余额 
        {
            return this.usermoney;
        }
        public void setUsermoney(int usermoney)         //设置用户余额
        {
            this.usermoney = usermoney;
        }
        public string getUseraccount()                  //得到用户账号
        {
            return this.useraccount;
        }
        public void setUseraccount(string useraccount)  //设置用户账号
        {
            this.useraccount = useraccount;
        }
        public string getUserpassword()                 //得到用户密码
        {
            return this.userpassword;
        }
        public void setUserpassword(string userpassword)//设置用户密码
        {
            this.userpassword = userpassword;
        }
    }
}
