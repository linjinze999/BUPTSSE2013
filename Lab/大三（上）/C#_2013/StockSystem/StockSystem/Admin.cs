using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using MySQLDriverCS;
using System.Threading;

using StockClass;

namespace StockSystem
{
    public partial class Admin : Form
    {
        private StockManage sm = new StockManage();//控制开盘、收盘
        //private bool run = true;
        //private Thread t;
        
        //void runThread() 
        //{
        //    while (run) 
        //    {
        //        System.Threading.Thread.Sleep(10000);
        //        getStockdevelop();
        //        getDeal();
        //        getMyorder();
        //    }
        //}

        public Admin()
        {
            InitializeComponent();
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
            Pen[] cs = new Pen[5] { Pens.Green, Pens.Red, Pens.Blue, Pens.Yellow, Pens.Purple };
            //提示折线颜色对应股票名
            Brush[] br = new Brush[5] { Brushes.Green, Brushes.Red, Brushes.Blue, Brushes.Yellow, Brushes.Purple };
            if (comboBox1.Text.CompareTo("所有股票") == 0)
            {
                int num = comboBox1.Items.Count;
                for (int m = 1; m < num; m++)
                {
                    float[] d = new float[10] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                    int j = 0;
                    try
                    {
                        StockDB sdb = new StockDB();
                        string tCmd = "select * from recent_deal where stock_name='" + comboBox1.Items[m].ToString() + "'";
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
                        MessageBox.Show("错误：'" + comboBox1.Items[m].ToString() + "'数据获取失败！", "股票趋势查看提示");
                        continue;
                    }

                    //提示折线颜色对应股票名
                    gph.DrawString(comboBox1.Items[m].ToString(), new Font("宋体", 11), br[m % br.Length], new PointF((m % 5) * 40 + 300, (m / 5) * 14 + 1));

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
                    string tCmd = "select * from recent_deal where stock_name='" + comboBox1.Text + "'";
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

        //交易详情
        public void getDeal()
        {
            try
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
                dataGridView1.DataSource = table;
                dataGridView1.Columns[1].Width = 110;
                dataGridView1.Columns[2].Width = 140;
                dataGridView1.Columns[3].Width = 140;
                dataGridView1.Columns[4].Width = 200;
                dataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            }
            catch {
                MessageBox.Show("错误：刷新时发生错误，请稍后再试！","交易详情提示");
            }
            
        }
        //重置股票趋势
        private void button1_Click(object sender, EventArgs e)
        {
            getStockdevelop();
        }
        //初始加载
        private void Admin_Load(object sender, EventArgs e)
        {
            comboBox3.Items.Add("所有订单");
            comboBox3.Items.Add("买方订单");
            comboBox3.Items.Add("卖方订单");
            comboBox3.Text = "所有订单";
            comboBox4.Items.Add("升序");
            comboBox4.Items.Add("降序");
            comboBox4.Text = "升序";
            comboBox5.Items.Add("用户名");
            comboBox5.Items.Add("股票名");
            comboBox5.Items.Add("交易类型");
            comboBox5.Items.Add("订单价格");
            comboBox5.Items.Add("订单数量");
            comboBox5.Items.Add("订单时间");
            comboBox5.Text = "订单时间";
            getAllstock();
            getStockdevelop();
            getDeal();
            getMyorder();
            getStockInfo();
            //t = new Thread(runThread);
            //t.Start();
        }
        //加载所有股票
        public void getAllstock() 
        {
            comboBox1.Items.Clear();
            comboBox2.Items.Clear();
            StockDB sdb = new StockDB();
            string tCmd = "select stock_name from stock_info";
            MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
            comboBox1.Items.Add("所有股票");
            comboBox1.Text = "所有股票";
            while (tReader.Read())
            {
                comboBox1.Items.Add(tReader.GetValue(0).ToString());
                comboBox2.Items.Add(tReader.GetValue(0).ToString());
            }
        }
        //刷新交易详情
        private void button2_Click(object sender, EventArgs e)
        {
            getDeal();
        }
        //显示订单
        public void getMyorder()
        {
            try
            {
                StockDB sdb = new StockDB();
                string tCmd = "";
                if (comboBox3.Text.CompareTo("买方订单") == 0)
                    tCmd = "select * from order_bs where order_type=0";
                else if (comboBox3.Text.CompareTo("卖方订单") == 0)
                    tCmd = "select * from order_bs where order_type=1";
                else
                    tCmd = "select * from order_bs";
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
                table.Columns[1].ColumnName = "用户名";
                table.Columns[2].ColumnName = "股票名";
                table.Columns[3].ColumnName = " 交易类型（0买1卖）";
                table.Columns[4].ColumnName = "订单价格";
                table.Columns[5].ColumnName = "订单数量";
                table.Columns[6].ColumnName = "订单时间";
                dataGridView2.DataSource = table;
                dataGridView2.Columns[0].Width = 80;
                dataGridView2.Columns[1].Width = 100;
                dataGridView2.Columns[2].Width = 100;
                dataGridView2.Columns[3].Width = 215;
                dataGridView2.Columns[4].Width = 120;
                dataGridView2.Columns[5].Width = 120;
                dataGridView2.Columns[6].Width = 200;
                dataGridView2.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
                int cl = 0;
                if (comboBox5.Text.CompareTo("用户名") == 0)
                    cl = 1;
                else if (comboBox5.Text.CompareTo("股票名") == 0)
                    cl = 2;
                else if (comboBox5.Text.CompareTo("交易类型") == 0)
                    cl = 3;
                else if (comboBox5.Text.CompareTo("订单价格") == 0)
                    cl = 4;
                else if (comboBox5.Text.CompareTo("订单数量") == 0)
                    cl = 5;
                else
                    cl = 6;
                if (comboBox4.Text.CompareTo("降序")==0)
                    dataGridView2.Sort(dataGridView2.Columns[cl], ListSortDirection.Descending);
                else
                    dataGridView2.Sort(dataGridView2.Columns[cl], ListSortDirection.Ascending);
            }
            catch {
                MessageBox.Show("错误：刷新时发生错误，请稍后再试！", "当前订单提示");
            }
            
        }
        //股票信息
        public void getStockInfo() 
        {
            try
            {
                StockDB sdb = new StockDB();
                string tCmd = "select * from stock_info";
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
                table.Columns[1].ColumnName = "股票ID";
                table.Columns[2].ColumnName = "股票名";
                table.Columns[3].ColumnName = "发行量";
                table.Columns[4].ColumnName = "股价";
                table.Columns[5].ColumnName = "待售量";
                dataGridView3.DataSource = table;
                dataGridView3.Columns[1].Width = 150;
                dataGridView3.Columns[2].Width = 150;
                dataGridView3.Columns[3].Width = 155;
                dataGridView3.Columns[4].Width = 150;
                dataGridView3.Columns[5].Visible = false;
                dataGridView3.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            }
            catch 
            {
                MessageBox.Show("错误：刷新时发生错误，请稍后再试！", "股票信息提示"); 
            }
        }
        //新股发行
        private void button5_Click(object sender, EventArgs e)
        {
            if (textBox2.Text == "" || textBox3.Text == "" || textBox4.Text == "")
                MessageBox.Show("错误：数据不完整！", "新股发行提示");
            else 
            {
                try
                {
                    StockDB sdb = new StockDB();
                    string tCmd = "select * from stock_info where stock_name='" + textBox2.Text + "'";
                    MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
                    if (tReader.Read())
                        MessageBox.Show("错误：已有相同股票名称！", "新股发行提示");
                    else
                    {
                        try
                        {
                            tCmd = "insert into stock_info(stock_name,stock_number,stock_money,stock_left) values('" + textBox2.Text + "'," +
                                int.Parse(textBox4.Text) + "," + double.Parse(textBox3.Text) + "," + int.Parse(textBox4.Text) + ")";
                            tReader = sdb.ExecuteCommand(tCmd);
                            tCmd = "select stock_id from stock_info where stock_name='" + textBox2.Text + "'";
                            tReader = sdb.ExecuteCommand(tCmd);
                            if (tReader.Read())
                                textBox1.Text = tReader.GetValue(0).ToString();
                            MessageBox.Show("成功：发行成功！该股票ID为：" + textBox1.Text, "新股发行提示");
                            getAllstock();
                            getStockInfo();
                        }
                        catch 
                        {
                            MessageBox.Show("错误：无法识别发行价或股票数量！", "新股发行提示");
                        }
                        
                    }

                }
                catch 
                {
                    MessageBox.Show("错误：发行时遇到错误，请稍后重试！", "新股发行提示");
                }
                
            }
        }
        //增加股票额度
        private void button6_Click(object sender, EventArgs e)
        {
            if (comboBox2.Text == "" || textBox5.Text == "")
                MessageBox.Show("错误：数据不完整！", "增加股票额度提示");
            else 
            {
                try
                {
                    StockDB sdb = new StockDB();
                    string tCmd = "select * from stock_info where stock_name='" + comboBox2.Text + "'";
                    MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
                    if (tReader.Read())
                    {
                        tCmd = "select stock_left from stock_info where stock_name='" + comboBox2.Text + "'";
                        tReader = sdb.ExecuteCommand(tCmd);
                        if (tReader.Read())
                        {
                            try
                            {
                                int add = int.Parse(tReader.GetValue(0).ToString());
                                add += int.Parse(textBox5.Text);
                                tCmd = "update stock_info set stock_left=" + add + " where stock_name='" + comboBox2.Text + "'";
                                tReader = sdb.ExecuteCommand(tCmd);
                                tCmd = "select stock_number from stock_info where stock_name='" + comboBox2.Text + "'";
                                tReader = sdb.ExecuteCommand(tCmd);
                                if (tReader.Read()) 
                                {
                                    int add2 = int.Parse(tReader.GetValue(0).ToString());
                                    add2 += int.Parse(textBox5.Text);
                                    tCmd = "update stock_info set stock_number=" + add2 + " where stock_name='" + comboBox2.Text + "'";
                                    tReader = sdb.ExecuteCommand(tCmd);
                                }
                                MessageBox.Show("成功：成功添加股票额度！", "增加股票额度提示");
                                getStockInfo();
                            }
                            catch
                            {
                                MessageBox.Show("错误：无法识别增加额度！", "增加股票额度提示");

                            }
                            
                        }
                    }
                    else
                        MessageBox.Show("错误：股票名称有误！", "增加股票额度提示");
                }
                catch {
                    MessageBox.Show("错误：增加股票额度时发生错误，请稍后重试！", "增加股票额度提示");
                }
                
            }
        }

        private void button8_Click(object sender, EventArgs e)
        {

        }
        //刷新订单
        private void button4_Click(object sender, EventArgs e)
        {
            getMyorder();
        }
        //开盘、收盘
        private void button3_Click(object sender, EventArgs e)
        {
            if (button3.Text.CompareTo("开盘") == 0)
            {
                sm.t_start();
                button3.Text = "收盘";
            }
            else
            {
                sm.t_close();
                button3.Text = "开盘";
            }
                
        }

        private void button7_Click(object sender, EventArgs e)
        {
            getStockInfo();
        }

        private void dataGridView3_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
