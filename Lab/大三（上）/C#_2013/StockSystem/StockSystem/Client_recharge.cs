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
    public partial class Client_recharge : Form
    {
        Call mycall;
        public Client_recharge(Call call)
        {
            this.mycall = call;
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text == "" || textBox2.Text == "")
            {
                MessageBox.Show("错误：请将数据填写完整！", "充值提示");
            }
            else
            {
                try
                {
                    double new_money = Convert.ToDouble(textBox2.Text);
                    StockDB sdb = new StockDB();
                    string tCmd = "select client_money from user_client where client_account='" + textBox1.Text + "'";
                    MySQLDataReader tReader = sdb.ExecuteCommand(tCmd);
                    if (tReader.Read())
                    {
                        new_money += Convert.ToDouble(tReader.GetValue(0).ToString());
                        tCmd = "update user_client set client_money=" + new_money + " where client_account='" + textBox1.Text + "'";
                        tReader = sdb.ExecuteCommand(tCmd);
                        mycall(new_money.ToString());
                        MessageBox.Show("成功：充值成功！", "充值提示");
                    }
                    else
                        MessageBox.Show("错误：找不到该用户！", "充值提示");
                }
                catch 
                {
                    MessageBox.Show("错误：金额格式有误！", "充值提示"); 
                }
                
            }
        }
    }
}
