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
    public partial class Client_changepassword : Form
    {
        Call my_call;
        public Client_changepassword(Call call)
        {
            my_call = call;
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox2.Text == "" || textBox3.Text == "" || textBox4.Text == "")
            {
                MessageBox.Show("错误：信息不完整！", "修改密码提示");
            }
            else if (textBox4.Text.CompareTo(textBox3.Text)!=0)
            {
                MessageBox.Show("错误：两次新密码不一致！", "修改密码提示");
            }
            else 
            {
                StockDB dbhandle = new StockDB();
                string tCmd = "select * from user_client where client_account='" + textBox1.Text + "' and client_password='" + textBox2.Text + "'";
                MySQLDataReader tReader = dbhandle.ExecuteCommand(tCmd);
                if (tReader.Read()) 
                {
                    tCmd = "update user_client set client_password='" + textBox3.Text + "' where client_account='" + textBox1.Text + "'";
                    tReader = dbhandle.ExecuteCommand(tCmd);
                    my_call(textBox3.Text);
                    MessageBox.Show("成功：修改密码成功！", "修改密码提示");
                }
                else
                    MessageBox.Show("错误：旧密码不匹配！", "修改密码提示");
            }
        }

        public void setAccount(string account) 
        {
            textBox1.Text = account;
        }
    }
}
