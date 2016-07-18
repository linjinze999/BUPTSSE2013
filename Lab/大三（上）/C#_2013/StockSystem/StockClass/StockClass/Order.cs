using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StockClass
{
    //订单信息
    public class Order
    {
        private int ordertype;      //订单类型，0代表买，1代表卖
        private User user;          //操作用户
        private Stock stock;        //操作股票
        private DateTime time;      //订单时间
         
        public Order() //无参初始化
        {
            this.ordertype = 0;
            this.user = new User();
            this.stock = new Stock();
            this.time = new DateTime();
        }
        public Order(int ordertype, User user, Stock stock, DateTime time) //有参初始化
        {
            this.ordertype = ordertype;
            this.user = user;
            this.stock = stock;
            this.time = time;
        }

        public int getOrdertype()               //得到订单类型
        {
            return this.ordertype;
        }
        public void setOrdertype(int ordertype) //设置订单类型
        {
            this.ordertype = ordertype;
        }
        public User getUser()                   //得到操作用户
        {
            return this.user;
        }
        public void setUser(User user)          //设置操作用户
        {
            this.user = user;
        }
        public Stock getStock()                 //得到操作股票
        {
            return this.stock;
        }
        public void setStock(Stock stock)       //设置操作股票
        {
            this.stock = stock;
        }
        public DateTime getTime()               //得到订单时间
        {
            return this.time;
        }
        public void setTime(DateTime time)      //设置订单时间
        {
            this.time = time;
        }
    }
}
