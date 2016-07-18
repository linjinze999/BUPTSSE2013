using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using MySQLDriverCS;
using System.Threading;

namespace StockClass
{
    public class StockManage
    {
        private StockDB sdb = new StockDB();
        private Thread t;
        private bool run = true;
        public StockManage(){}

        //开盘
        public void t_start() 
        {
            this.run = true;
            t = new Thread(runDealOrder);
            t.Start();
        }
        //收盘
        public void t_close() 
        {
            this.run = false;
        }
        //重写thread.run
        void runDealOrder()
        {
            while (run) 
            {
                DealOrder();
                System.Threading.Thread.Sleep(10000);
            }
        }

        //交易撮合
        public void DealOrder()
        {
            string str = "select * from stock_info";
            MySQLDataReader da = sdb.ExecuteCommand(str);
            if (da.Read()) 
            {
                //得到所有股票名
                List<string> stock_names = new List<string>();
                do{
                    stock_names.Add(da.GetValue(1).ToString());
                }while(da.Read());
                //处理每一个股票的订单
                foreach (string stock_name in stock_names) 
                {
                    //获取订单
                    List<Order> order_buy = selectOrderBuy(stock_name);
                    List<Order> order_sell = selectOrderSell(stock_name);
                    //订单为空
                    if (order_buy.Count == 0 || order_sell.Count == 0)
                        continue;
                    //为订单排序
                    order_buy.Sort(new ComparePriceDESC());
                    order_sell.Sort(new ComparePriceASC());
                    //买方最高价低于卖方最低价
                    if (order_buy[0].getStock().getStockmoney() < order_sell[0].getStock().getStockmoney())
                        continue;

                    //计算可交易价数量s,t
                    int s = 0;
                    int t = 0;
                    for (int i = 0; i < order_buy.Count; i++)
                    {
                        if (order_buy[s].getStock().getStockmoney() < order_sell[0].getStock().getStockmoney())
                            break;
                        else
                            s = i;
                    }
                    for (int i = 0; i < order_sell.Count; i++)
                    {
                        if (order_sell[t].getStock().getStockmoney() > order_buy[0].getStock().getStockmoney())
                            break;
                        else
                            t = i;
                    }
                    //计算买方交易价的成交数量
                    List<int> order_buy_number = new List<int>();
                    for (int i = 0; i <= s; i++)
                    {
                        int buy_number = 0;
                        for (int n = 0; n <= i; n++)
                            buy_number += order_buy[n].getStock().getStocknumber();
                        int sell_number = 0;
                        for (int n = 0; n <= t; n++)
                            if (order_sell[n].getStock().getStockmoney() <= order_buy[i].getStock().getStockmoney())
                                sell_number += order_sell[n].getStock().getStocknumber();
                            else
                                break;
                        if (buy_number > sell_number)
                            order_buy_number.Add(sell_number);
                        else
                            order_buy_number.Add(buy_number);
                    }
                    //计算卖方交易价的成交数量
                    List<int> order_sell_number = new List<int>();
                    for (int i = 0; i <= t; i++)
                    {
                        int buy_number = 0;
                        for (int n = 0; n <= s; n++)
                            if (order_sell[i].getStock().getStockmoney() <= order_buy[n].getStock().getStockmoney())
                                buy_number += order_buy[n].getStock().getStocknumber();
                            else
                                break;
                        int sell_number = 0;
                        for (int n = 0; n <= i; n++)
                            sell_number += order_sell[n].getStock().getStocknumber();

                        if (buy_number > sell_number)
                            order_sell_number.Add(sell_number);
                        else
                            order_sell_number.Add(buy_number);
                    }
                    //得出成交价格及其数量
                    int buy_max = 0;
                    double buy_money = 0;
                    for (int m = 0; m < order_buy_number.Count; m++)
                        if (order_buy_number[m] > buy_max)
                        {
                            buy_max = order_buy_number[m];
                            buy_money = order_buy[m].getStock().getStockmoney();
                        }
                    int sell_max = 0;
                    double sell_money = 0;
                    for (int m = 0; m < order_sell_number.Count; m++)
                        if (order_sell_number[m] > sell_max)
                        {
                            sell_max = order_sell_number[m];
                            sell_money = order_sell[m].getStock().getStockmoney();
                        }
                    int deal_number = 0;
                    double deal_money = 0;
                    if (buy_max > sell_max)
                    {
                        deal_number = buy_max;
                        deal_money = buy_money;
                    }
                    else
                    {
                        deal_number = sell_max;
                        deal_money = sell_money;
                    }
                    //更改股票市值并记录
                    str = "update stock_info set stock_money = " + deal_money + " where stock_name = '" + stock_name + "'";
                    sdb.ExecuteCommand(str);
                    str = "insert into recent_deal(stock_name,deal_price,deal_number,deal_time) values('" + stock_name + "'," +
                        deal_money + "," + deal_number + ",'" + DateTime.Now + "')";
                    sdb.ExecuteCommand(str);
                    //处理买方订单
                    Deal_order_buy(order_buy, deal_number);
                    //处理卖方订单
                    Deal_order_sell(order_sell, deal_number);
                }
            }
            else
                return;
        }

        //获取买方订单
        List<Order> selectOrderBuy(string stock_name) 
        {
            List<Order> order_buy = new List<Order>();
            order_buy.Clear();
            string str = "select * from order_bs where order_type = 0 and stock_name = '" + stock_name + "'";
            MySQLDataReader da = sdb.ExecuteCommand(str);
            while (da.Read()) 
            {
                Order od = new Order();
                od.setOrdertype(0);
                od.setUser(new User(0, 0, da.GetValue(0).ToString(), ""));
                od.setStock(new Stock(0, int.Parse(da.GetValue(4).ToString()), double.Parse(da.GetValue(3).ToString()), da.GetValue(1).ToString()));
                od.setTime(DateTime.Parse(da.GetValue(5).ToString()));
                order_buy.Add(od);
            }
            return order_buy;
        }
        //获取卖方订单
        List<Order> selectOrderSell(string stock_name)
        {
            List<Order> order_sell = new List<Order>();
            order_sell.Clear();
            string str = "select * from order_bs where order_type = 1 and stock_name = '" + stock_name + "'";
            MySQLDataReader da = sdb.ExecuteCommand(str);
            while (da.Read())
            {
                Order od = new Order();
                od.setOrdertype(1);
                od.setUser(new User(0, 0, da.GetValue(0).ToString(), ""));
                od.setStock(new Stock(0, int.Parse(da.GetValue(4).ToString()), double.Parse(da.GetValue(3).ToString()), da.GetValue(1).ToString()));
                od.setTime(DateTime.Parse(da.GetValue(5).ToString()));
                order_sell.Add(od);
            }
            str = "select * from stock_info where stock_left > 0 and stock_name = '" + stock_name + "'";
            da = sdb.ExecuteCommand(str);
            while (da.Read())
            {
                Order od = new Order();
                od.setOrdertype(2);
                od.setUser(new User(0, 0, da.GetValue(1).ToString(), ""));
                od.setStock(new Stock(int.Parse(da.GetValue(0).ToString()), int.Parse(da.GetValue(4).ToString()), double.Parse(da.GetValue(3).ToString()), da.GetValue(1).ToString()));
                od.setTime(DateTime.Now);
                order_sell.Add(od);
            }
            return order_sell;
        }
        //价格升序
        class ComparePriceASC : IComparer<Order>
        {
            public int Compare(Order a, Order b)
            {
                return a.getStock().getStockmoney().CompareTo(b.getStock().getStockmoney());
            }
        }
        //价格降序
        class ComparePriceDESC : IComparer<Order>
        {
            public int Compare(Order a, Order b)
            {
                if (a.getStock().getStockmoney().CompareTo(b.getStock().getStockmoney()) == 0)
                    return 0;
                else if (a.getStock().getStockmoney().CompareTo(b.getStock().getStockmoney()) > 0)
                    return -1;
                else
                    return 1;
            }
        }
        //处理买方交易
        void Deal_order_buy(List<Order> buy,int number) 
        {
            int no = 0;
            while (number > 0) 
            {
                //移除订单
                string str = "delete from order_bs where client_account='" + buy[no].getUser().getUseraccount() + "' and stock_name='" + buy[no].getStock().getStockname() + "' and order_type=0 and order_time='" + buy[no].getTime() + "'";
                sdb.ExecuteCommand(str);
                if (buy[no].getStock().getStocknumber() < number) 
                {
                    //修改用户余额
                    double buy_money = buy[no].getStock().getStockmoney() * buy[no].getStock().getStocknumber();
                    str = "update user_client set client_money = client_money - " + buy_money + " where client_account='" + buy[no].getUser().getUseraccount() + "'";
                    sdb.ExecuteCommand(str);
                    //修改拥有股票
                    str = "select * from stock_ownner where stock_name='" + buy[no].getStock().getStockname() + "' and client_account='" + buy[no].getUser().getUseraccount()+"'";
                    MySQLDataReader da = sdb.ExecuteCommand(str);
                    if (da.Read()) 
                    {
                        str = "update stock_ownner set ownner_stock_number = ownner_stock_number + " + buy[no].getStock().getStocknumber() + " where stock_name='" +
                            buy[no].getStock().getStockname() + "' and client_account='" + buy[no].getUser().getUseraccount() + "'";
                        sdb.ExecuteCommand(str);
                    }
                    else
                    {
                        str = "insert into stock_ownner(stock_name,client_account,ownner_stock_number) values('" + buy[no].getStock().getStockname() + "','" +
                            buy[no].getUser().getUseraccount() + "'," + buy[no].getStock().getStocknumber() + "";
                        sdb.ExecuteCommand(str);
                    }
                    number -= buy[no].getStock().getStocknumber();
                }
                else
                {
                    //修改用户余额
                    double buy_money = buy[no].getStock().getStockmoney() * number;
                    str = "update user_client set client_money = client_money - " + buy_money + " where client_account='" + buy[no].getUser().getUseraccount() + "'";
                    sdb.ExecuteCommand(str);
                    //修改拥有股票
                    str = "select * from stock_ownner where stock_name='" + buy[no].getStock().getStockname() + "' and client_account='" + buy[no].getUser().getUseraccount() + "'";
                    MySQLDataReader da = sdb.ExecuteCommand(str);
                    if (da.Read())
                    {
                        str = "update stock_ownner set ownner_stock_number = ownner_stock_number + " + number + " where stock_name='" +
                            buy[no].getStock().getStockname() + "' and client_account='" + buy[no].getUser().getUseraccount() + "'";
                        sdb.ExecuteCommand(str);
                    }
                    else
                    {
                        str = "insert into stock_ownner(stock_name,client_account,ownner_stock_number) values('" + buy[no].getStock().getStockname() + "','" +
                            buy[no].getUser().getUseraccount() + "'," + number + "";
                        sdb.ExecuteCommand(str);
                    }
                    number = 0;
                }
                no++;
            }
        }
        //处理卖方交易
        void Deal_order_sell(List<Order> sell, int number) 
        {
            int no = 0;
            while (number > 0)
            {
                //移除订单
                string str = "delete from order_bs where client_account='" + sell[no].getUser().getUseraccount() + "' and stock_name='" + sell[no].getStock().getStockname() + "' and order_type=1 and order_time='" + sell[no].getTime() + "'";
                sdb.ExecuteCommand(str);
                if (sell[no].getOrdertype() == 1) //股民卖
                {
                    if (sell[no].getStock().getStocknumber() < number)
                    {
                        //修改用户余额
                        double sell_money = sell[no].getStock().getStockmoney() * sell[no].getStock().getStocknumber();
                        str = "update user_client set client_money = client_money + " + sell_money + " where client_account='" + sell[no].getUser().getUseraccount() + "'";
                        sdb.ExecuteCommand(str);
                        //修改拥有股票
                        str = "select * from stock_ownner where stock_name='" + sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                        MySQLDataReader da = sdb.ExecuteCommand(str);
                        if (da.Read())
                        {
                            str = "update stock_ownner set ownner_stock_number = ownner_stock_number - " + sell[no].getStock().getStocknumber() + " where stock_name='" +
                                sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                            sdb.ExecuteCommand(str);
                        }
                        number -= sell[no].getStock().getStocknumber();
                    }
                    else 
                    {
                        //修改用户余额
                        double buy_money = sell[no].getStock().getStockmoney() * number;
                        str = "update user_client set client_money = client_money + " + buy_money + " where client_account='" + sell[no].getUser().getUseraccount() + "'";
                        sdb.ExecuteCommand(str);
                        //修改拥有股票
                        str = "select * from stock_ownner where stock_name='" + sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                        MySQLDataReader da = sdb.ExecuteCommand(str);
                        if (da.Read())
                        {
                            str = "update stock_ownner set ownner_stock_number = ownner_stock_number - " + number + " where stock_name='" +
                                sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                            sdb.ExecuteCommand(str);
                        }
                        number = 0;
                    }
                    
                }
                else//公司卖
                {
                    if (sell[no].getStock().getStocknumber() > number)
                        str = "update stock_info set stock_left = stock_left - " + number + " where stock_name='" +
                             sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                    else
                        str = "update stock_info set stock_left = 0 where stock_name='" + sell[no].getStock().getStockname() + "' and client_account='" + sell[no].getUser().getUseraccount() + "'";
                    sdb.ExecuteCommand(str);
                }
            }
        }
    }
}
