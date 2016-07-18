using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StockClass
{
    //股票信息
    public class Stock
    {
        private int stockid;        //股票ID
        private int stocknumber;    //股票数量
        private double stockmoney;  //股票金额
        private string stockname;   //股票名

        public Stock()//无参初始化 
        {
            this.stockid = 0;
            this.stocknumber = 0;
            this.stockmoney = 0;
            this.stockname = "";
        }
        public Stock(int stockid, int stocknumber, double stockmoney, string stockname) //有参初始化
        {
            this.stockid = stockid;
            this.stocknumber = stocknumber;
            this.stockmoney = stockmoney;
            this.stockname = stockname;
        }

        public int getStockid()                     //得到股票ID
        {
            return this.stockid;
        }
        public void setStockid(int stockid)         //设置股票ID 
        {
            this.stockid = stockid;
        }
        public int getStocknumber()                 //得到股票数量
        {
            return this.stocknumber;
        }
        public void setStocknumber(int stocknumber) //设置股票数量 
        {
            this.stocknumber = stocknumber;
        }
        public double getStockmoney()                  //得到股票金额
        {
            return this.stockmoney;
        }
        public void setStockmoney(double stockmoney)   //设置股票金额
        {
            this.stockmoney = stockmoney;
        }
        public string getStockname()                //得到股票名
        {
            return this.stockname;
        }
        public void setStockname(string stockname)  //设置股票名
        {
            this.stockname = stockname;
        }
    }
}
