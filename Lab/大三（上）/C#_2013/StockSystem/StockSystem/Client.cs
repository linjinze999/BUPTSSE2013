using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using MySQLDriverCS;

using StockClass;

namespace StockSystem
{
    public partial class Client : Form
    {
        //设置账号
        public void setClientaccount(string clientaccount){
            CA_text_account.Text = clientaccount;
        }
        public Client()
        {
            InitializeComponent();
        }
        //充值按钮
        private void button1_Click(object sender, EventArgs e)
        {
            Call new_call;
            new_call = new Call(setCA_text_balance);
            Client_recharge cr = new Client_recharge(new_call);
            cr.Show();
        }
        //初始加载
        private void Client_Load(object sender, EventArgs e)
        {
            StockDB sdb = new StockDB();
            string tCmd = "select * from user_client where client_account=" + CA_text_account.Text + "";
            MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
            if (tReader.Read())
                CA_text_balance.Text = tReader.GetValue(2).ToString();
            CA_text_password.Text = tReader.GetValue(1).ToString();

            tCmd = "select stock_name from stock_info";
            tReader = sdb.ExecuteCommand(tCmd);
            CS_comboBox1.Items.Add("所有股票");
            CS_comboBox1.Text = "所有股票";
            while (tReader.Read()) 
            {
                CO_comboBox1.Items.Add(tReader.GetValue(0).ToString());
                CS_comboBox1.Items.Add(tReader.GetValue(0).ToString());
            }
            
            getMystock();
            getMyorder();
            getStockdevelop();
            getDeal();
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
        //持有股票
        public void getMystock() {
            StockDB sdb = new StockDB();
            string tCmd = "select * from stock_ownner where client_account='" + CA_text_account.Text + "'";
            MySQLDataAdapter adapter = sdb.ECByAdapter(tCmd);
            DataSet ds = new DataSet();
            adapter.Fill(ds);
            System.Data.DataTable table = new DataTable();
            System.Data.DataColumn column = new DataColumn();
            column.ColumnName = "序号";
            column.AutoIncrement = true;
            column.AutoIncrementSeed = 1;
            column.AutoIncrementStep = 1;
            table.Columns.Add(column);
            table.Merge(ds.Tables[0]);
            table.Columns[1].ColumnName = "股票名";
            table.Columns[3].ColumnName = "股票数量";
            CA_GV.DataSource = table;
            CA_GV.Columns[0].Width = 150;
            CA_GV.Columns[1].Width = 280;
            CA_GV.Columns[2].Visible = false;
            CA_GV.Columns[3].Width = 280;
            CA_GV.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }
        //我的订单
        public void getMyorder()
        {
            StockDB sdb = new StockDB();
            string tCmd = "select * from order_bs where client_account='" + CA_text_account.Text + "'";
            MySQLDataAdapter adapter = sdb.ECByAdapter(tCmd);
            DataSet ds = new DataSet();
            adapter.Fill(ds);
            System.Data.DataTable table = new DataTable();
            System.Data.DataColumn column = new DataColumn();
            column.ColumnName = "序号";
            column.AutoIncrement = true;
            column.AutoIncrementSeed = 1;
            column.AutoIncrementStep = 1;
            table.Columns.Add(column);
            table.Merge(ds.Tables[0]);
            table.Columns[2].ColumnName = "股票名";
            table.Columns[3].ColumnName = "交易类型（0买1卖）";
            table.Columns[4].ColumnName = "订单价格";
            table.Columns[5].ColumnName = "订单数量";
            table.Columns[6].ColumnName = "订单时间";
            CO_GV.DataSource = table;
            CO_GV.Columns[0].Width = 75;
            CO_GV.Columns[1].Visible = false;
            CO_GV.Columns[2].Width = 100;
            CO_GV.Columns[3].Width = 210;
            CO_GV.Columns[4].Width = 115;
            CO_GV.Columns[5].Width = 115;
            CO_GV.Columns[6].Width = 200;
            CO_GV.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            comboBox1.Items.Clear();
            for (int i = 1; i <= CO_GV.RowCount; i++)
                comboBox1.Items.Add(i + "");
        }
        //设置余额
        public void setCA_text_balance(string new_money) 
        {
            CA_text_balance.Text = new_money;
        }
        //设置密码
        public void setCA_text_password(string new_password)
        {
            CA_text_password.Text = new_password;
        }
        //刷新持有股票
        private void CA_button_refresh_Click(object sender, EventArgs e)
        {
            getMystock();
        }
        //更改密码
        private void CA_button_changepassword_Click(object sender, EventArgs e)
        {
            Client_changepassword cc = new Client_changepassword(new Call(setCA_text_password));
            cc.setAccount(CA_text_account.Text);
            cc.Show();
        }
        //提交订单
        private void CO_button_submit_Click(object sender, EventArgs e)
        {
            if (!CO_rb_buy.Checked && !CO_rb_sell.Checked)
            {
                MessageBox.Show("错误：请选择交易类型！", "订单提示");
            }
            else if (CO_comboBox1.Text == "" || textBox1.Text == "" || textBox2.Text == "")
                MessageBox.Show("错误：交易信息不完整！", "订单提示");
            else
            {
                int ordertype = 0;
                if (CO_rb_sell.Checked)
                    ordertype = 1;
                User user = new User(0, int.Parse(CA_text_balance.Text), CA_text_account.Text, CA_text_password.Text);
                StockDB sdb = new StockDB();
                string tCmd = "select stock_id from stock_info where stock_name='" + CO_comboBox1.Text + "'";
                MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
                if (tReader.Read())
                {
                    int stock_id = int.Parse(tReader.GetValue(0).ToString());
                    try 
                    {
                        Stock stock = new Stock(stock_id, int.Parse(textBox2.Text), double.Parse(textBox1.Text), CO_comboBox1.Text);
                        Order order = new Order(ordertype, user, stock, DateTime.Now);
                        if (ordertype == 0)
                        {
                            //判断购买订单是否符合条件
                            tCmd = "select client_money from user_client where client_account='" + user.getUseraccount() + "'";
                            tReader = sdb.ExecuteCommand(tCmd);
                            if (tReader.Read())
                            {
                                double left = double.Parse(tReader.GetValue(0).ToString());//得到用户剩余金额
                                tCmd = "select * from order_bs where client_account='" + CA_text_account.Text + "' and order_type=0";
                                tReader = sdb.ExecuteCommand(tCmd);
                                double lm = 0;
                                while (tReader.Read())
                                {
                                    lm += double.Parse(tReader.GetValue(3).ToString()) * int.Parse(tReader.GetValue(4).ToString());
                                }
                                if ((left - lm - order.getStock().getStockmoney() * order.getStock().getStocknumber()) >= 0)
                                {
                                    tCmd = "insert into order_bs(client_account,stock_name,order_type,order_price,order_number,order_time) values('" + order.getUser().getUseraccount() + "','" +
                                                            order.getStock().getStockname() + "'," + order.getOrdertype() + "," + order.getStock().getStockmoney() + "," + order.getStock().getStocknumber() + ",'" + order.getTime() + "')";
                                    try
                                    {
                                        sdb.ExecuteCommand(tCmd);
                                        MessageBox.Show("成功：订单提交成功！", "订单提示");
                                        getMyorder();
                                    }
                                    catch
                                    {
                                        MessageBox.Show("错误：在提交订单时发生未知错误，请稍后重试！", "订单提示");
                                    }
                                }
                                else
                                    MessageBox.Show("错误：用户余额不足，请检查余额与购买订单！", "订单提示");

                            }
                        }
                        else
                        {
                            //判断卖方订单是否符合要求
                            tCmd = "select ownner_stock_number from stock_ownner where client_account='" + user.getUseraccount() + "' and stock_name='" + order.getStock().getStockname() + "'";
                            tReader = sdb.ExecuteCommand(tCmd);
                            if (tReader.Read())
                            {
                                int left = 0;
                                left = int.Parse(tReader.GetValue(0).ToString());//得到用户持有股票
                                tCmd = "select * from order_bs where client_account='" + CA_text_account.Text + "' and order_type=1";
                                tReader = sdb.ExecuteCommand(tCmd);
                                int lm = 0;
                                while (tReader.Read())
                                {
                                    lm += int.Parse(tReader.GetValue(4).ToString());
                                }
                                if ((left - lm - order.getStock().getStocknumber()) >= 0)
                                {
                                    tCmd = "insert into order_bs(client_account,stock_name,order_type,order_price,order_number,order_time) values('" + order.getUser().getUseraccount() + "','" +
                                                            order.getStock().getStockname() + "'," + order.getOrdertype() + "," + order.getStock().getStockmoney() + "," + order.getStock().getStocknumber() + ",'" + order.getTime() + "')";
                                    try
                                    {
                                        sdb.ExecuteCommand(tCmd);
                                        MessageBox.Show("成功：订单提交成功！", "订单提示");
                                        getMyorder();
                                    }
                                    catch
                                    {
                                        MessageBox.Show("错误：在提交订单时发生未知错误，请稍后重试！", "订单提示");
                                    }
                                }
                                else
                                    MessageBox.Show("错误：持有股票不足，请检查持有股票与卖出订单！", "订单提示");

                            }
                            else
                                MessageBox.Show("错误：您未持有该股票！", "订单提示");
                        }
                    }
                    catch 
                    {
                        MessageBox.Show("错误：无法识别金额或数量！", "订单提示");
                    }  
                }
                else
                {
                    MessageBox.Show("错误：股票名不正确，请从下拉框中选取！", "订单提示");
                }
            }
        }
        //刷新我的订单
        private void CO_button_refresh_Click(object sender, EventArgs e)
        {
            getMyorder();
        }
        //交易详情
        public void getDeal() 
        {
            StockDB sdb = new StockDB();
            string tCmd = "select * from recent_deal";
            MySQLDataAdapter adapter = sdb.ECByAdapter(tCmd);
            DataSet ds = new DataSet();
            adapter.Fill(ds);
            System.Data.DataTable table = new DataTable();
            System.Data.DataColumn column = new DataColumn();
            column.ColumnName = "序号";
            column.AutoIncrement = true;
            column.AutoIncrementSeed = 1;
            column.AutoIncrementStep = 1;
            table.Columns.Add(column);
            table.Merge(ds.Tables[0]);
            table.Columns[1].ColumnName = "股票名";
            table.Columns[2].ColumnName = "交易价格";
            table.Columns[3].ColumnName = "交易数量";
            table.Columns[4].ColumnName = "交易时间";
            CS_GV.DataSource = table;
            CS_GV.Columns[1].Width = 110;
            CS_GV.Columns[2].Width = 140;
            CS_GV.Columns[3].Width = 140;
            CS_GV.Columns[4].Width = 200;
            CS_GV.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }
        //刷新交易详情
        private void CS_button_refresh2_Click(object sender, EventArgs e)
        {
            getDeal();
        }
        //股票走势
        public void getStockdevelop() 
        {
            Bitmap bm = new Bitmap(750, 260);
            Graphics gph = Graphics.FromImage(bm);
            gph.Clear(Color.Transparent);
            PointF cPt = new PointF(30, 230);//中心点
            gph.DrawString("0", new Font("宋体", 11), Brushes.Black, new PointF(cPt.X - 5, cPt.Y + 5));
            PointF[] xPt = new PointF[3] { new PointF(740, cPt.Y), new PointF(730, cPt.Y - 6), new PointF(730, cPt.Y + 6) };//X三角形
            PointF[] yPt = new PointF[3] { new PointF(cPt.X, 12), new PointF(cPt.X - 7, 22), new PointF(cPt.X + 7, 22) };//Y三角形
            gph.DrawString("股票价格走势", new Font("华文楷体", 14), Brushes.Black, new PointF(200, 0));//标题
            //画X轴
            gph.DrawLine(Pens.Black, cPt.X, cPt.Y, 730, cPt.Y);
            gph.DrawPolygon(Pens.Black, xPt);
            gph.FillPolygon(new SolidBrush(Color.Black), xPt);
            gph.DrawString("时间", new Font("宋体", 11), Brushes.Black, new PointF(700, cPt.Y + 10));
            //画Y轴
            gph.DrawLine(Pens.Black, cPt.X, cPt.Y, cPt.X, 22);
            gph.DrawPolygon(Pens.Black, yPt);
            gph.FillPolygon(new SolidBrush(Color.Black), yPt);
            gph.DrawString("价格（元）", new Font("宋体", 11), Brushes.Black, new PointF(0, 0));
            for (int i = 1; i <= 10; i++)
            {
                //画Y刻度
                if (i < 10)
                {
                    gph.DrawString((i * 0.2).ToString(), new Font("宋体", 11), Brushes.Black, new PointF(cPt.X - 30, cPt.Y - i * 23 - 2));
                    gph.DrawLine(Pens.Black, cPt.X - 3, cPt.Y - i * 23, cPt.X, cPt.Y - i * 23);
                }
                //画X轴项目
                gph.DrawString(i.ToString(), new Font("宋体", 11), Brushes.Black, new PointF(cPt.X + i * 65 - 8, cPt.Y + 5));
                gph.DrawLine(Pens.Black, cPt.X + i * 65, cPt.Y + 3, cPt.X + i * 65, cPt.Y);
            }
            //折线颜色
            Pen[] cs = new Pen[5] { Pens.Green, Pens.Red, Pens.Blue,Pens.Yellow,Pens.Purple };
            //提示折线颜色对应股票名
            Brush[] br = new Brush[5] { Brushes.Green, Brushes.Red, Brushes.Blue, Brushes.Yellow, Brushes.Purple };
            if (CS_comboBox1.Text.CompareTo("所有股票") == 0) 
            {
                int num = CS_comboBox1.Items.Count;
                for (int m = 1; m < num; m++) 
                {
                    float[] d = new float[10] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                    int j = 0;
                    try
                    {
                        StockDB sdb = new StockDB();
                        string tCmd = "select * from recent_deal where stock_name='" + CS_comboBox1.Items[m].ToString() + "'";
                        MySQLDataAdapter adapter = sdb.ECByAdapter(tCmd);
                        DataSet ds = new DataSet();
                        adapter.Fill(ds);
                        int p = 0;
                        if (ds.Tables[0].Rows.Count - 10 > 0)
                            p = ds.Tables[0].Rows.Count - 10;
                        for (int i = ds.Tables[0].Rows.Count; i > 0 && i > ds.Tables[0].Rows.Count - 10; i--)
                        {
                            d[j] = float.Parse(ds.Tables[0].Rows[p + j][1].ToString());
                            j++;
                        }
                    }
                    catch
                    {
                        MessageBox.Show("错误：'" + CS_comboBox1.Items[m].ToString() + "'数据获取失败！", "股票趋势查看提示");
                        continue;
                    }

                    //提示折线颜色对应股票名
                    gph.DrawString(CS_comboBox1.Items[m].ToString(), new Font("宋体", 11), br[m % br.Length], new PointF((m % 5) * 40 + 300, (m / 5) * 14 + 1));

                    for (int i = 1; i <= j; i++)
                    {
                        //画点
                        gph.DrawEllipse(Pens.Black, cPt.X + i * 65 - 1.5F, cPt.Y - d[i - 1] * 120 - 1.5F, 3, 3);
                        gph.FillEllipse(new SolidBrush(Color.Black), cPt.X + i * 65 - 1.5F, cPt.Y - d[i - 1] * 120 - 1.5F, 3, 3);
                        //画数值
                        gph.DrawString(d[i - 1].ToString(), new Font("宋体", 10), Brushes.Black, new PointF(cPt.X + i * 65, cPt.Y - d[i - 1] * 120));
                        //画折线
                        if (i > 1)
                        {
                            gph.DrawLine(cs[m % cs.Length], cPt.X + (i - 1) * 65, cPt.Y - d[i - 2] * 120, cPt.X + i * 65, cPt.Y - d[i - 1] * 120);
                        }
                    }
                }
            }
            else 
            {
                float[] d = new float[10] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                int j = 0;
                try
                {
                    StockDB sdb = new StockDB();
                    string tCmd = "select * from recent_deal where stock_name='" + CS_comboBox1.Text + "'";
                    MySQLDataAdapter adapter = sdb.ECByAdapter(tCmd);
                    DataSet ds = new DataSet();
                    adapter.Fill(ds);
                    int p = 0;
                    if (ds.Tables[0].Rows.Count - 10 > 0)
                        p = ds.Tables[0].Rows.Count - 10;
                    for (int i = ds.Tables[0].Rows.Count; i > 0 && i > ds.Tables[0].Rows.Count - 10; i--)
                    {
                        d[j] = float.Parse(ds.Tables[0].Rows[p + j][1].ToString());
                        j++;
                    }
                }
                catch
                {
                    MessageBox.Show("错误：数据获取失败！", "股票趋势查看提示");
                }


                for (int i = 1; i <= j; i++)
                {
                    //画点
                    gph.DrawEllipse(Pens.Black, cPt.X + i * 65 - 1.5F, cPt.Y - d[i - 1] * 120 - 1.5F, 3, 3);
                    gph.FillEllipse(new SolidBrush(Color.Black), cPt.X + i * 65 - 1.5F, cPt.Y - d[i - 1] * 120 - 1.5F, 3, 3);
                    //画数值
                    gph.DrawString(d[i - 1].ToString(), new Font("宋体", 10), Brushes.Black, new PointF(cPt.X + i * 65, cPt.Y - d[i - 1] * 120));
                    //画折线
                    if (i > 1)
                    {
                        gph.DrawLine(Pens.Red, cPt.X + (i - 1) * 65, cPt.Y - d[i - 2] * 120, cPt.X + i * 65, cPt.Y - d[i - 1] * 120);
                    }
                }
            }
            
            
            pictureBox1.Image = bm;
        }
        //重置股票走势
        private void CS_button1_Click(object sender, EventArgs e)
        {
            getStockdevelop();
        }
        //删除订单
        private void button1_Click_1(object sender, EventArgs e)
        {
            try
            {
                int row = int.Parse(comboBox1.Text) - 1;
                string str = "delete from order_bs where client_account='" + CO_GV.Rows[row].Cells[1].Value + "' and stock_name='" + CO_GV.Rows[row].Cells[2].Value + 
                    "' and order_type=" + CO_GV.Rows[row].Cells[3].Value + " and order_time='" + CO_GV.Rows[row].Cells[6].Value + "'";
                StockDB sdb = new StockDB();
                sdb.ExecuteCommand(str);
                MessageBox.Show("成功：删除订单成功！", "删除订单提示");
                getMyorder();
            }
            catch 
            {
                MessageBox.Show("错误：删除订单出错，请检查序号或稍后重试！","删除订单提示");
            }
            
        }

    }
}
