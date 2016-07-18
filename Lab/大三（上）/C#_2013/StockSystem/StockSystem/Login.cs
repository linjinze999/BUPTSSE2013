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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }


        private void button_login_Click(object sender, EventArgs e)
        {
            if (input_account.Text == "" || input_password.Text == "") 
            { 
                MessageBox.Show("错误：账号或密码为空！","登录提示");
            }
            else if(!accounttype_client.Checked && !accounttype_admin.Checked)
            {
                MessageBox.Show("错误：未选择用户类型！", "登录提示");
            }
            else
            {
                try
                {
                    StockDB dbhandle = new StockDB();
                    string tCmd = "";
                    if (accounttype_admin.Checked)
                        tCmd = "select * from user_admin where admin_account=" + input_account.Text + " and admin_password=" + input_password.Text + "";
                    else
                        tCmd = "select * from user_client where client_account=" + input_account.Text + " and client_password=" + input_password.Text + "";
                    MySQLDataReader tReader = dbhandle.ExecuteCommand(tCmd);
                    
                    if (tReader.Read())
                    {
                        this.Hide();
                        if (accounttype_admin.Checked) 
                        {
                            Admin admin = new Admin();
                            admin.FormClosing += new FormClosingEventHandler(close);
                            admin.Show();
                        }
                        else
                        {
                            Client client = new Client();
                            client.FormClosing += new FormClosingEventHandler(close);
                            client.setClientaccount(input_account.Text);
                            client.Show();
                        }
                    }
                    else
                        MessageBox.Show("错误：账号或密码错误！", "登录提示");
                }
                catch
                {
                    MessageBox.Show("错误：连接数据库失败！", "登录提示");
                }
                
            }
            
        }

        private void input_account_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void input_password_TextChanged(object sender, EventArgs e)
        {

        }

        void close(object sender, FormClosingEventArgs e)
        {
            this.Close();
        }

        private void Login_Load(object sender, EventArgs e)
        {
            accounttype_client.Checked = true;
        }

        private void button_regist_Click(object sender, EventArgs e)
        {
            if (input_account.Text == "" || input_password.Text == "")
            {
                MessageBox.Show("错误：注册账号或密码为空！", "注册提示");
            }
            else if (!accounttype_client.Checked)
            {
                MessageBox.Show("错误：注册类型只能为用户！", "注册提示");
            }
            else
            {
                try
                {
                    StockDB dbhandle = new StockDB();
                    string tCmd = "select * from user_client where client_account=" + input_account.Text + "";
                    MySQLDataReader tReader = dbhandle.ExecuteCommand(tCmd);
                    if (!tReader.Read())
                    {
                        try
                        {
                            tCmd = "insert into user_client(client_account,client_password,client_money) values(" + input_account.Text + "," + input_password.Text + ",0)";
                            tReader = dbhandle.ExecuteCommand(tCmd);
                            MessageBox.Show("成功：注册成功！", "注册提示");
                        }
                        catch 
                        {
                            MessageBox.Show("错误：注册时发生未知错误，请稍后重试！", "注册提示");
                        }
                    }
                    else
                        MessageBox.Show("错误：已有相同账号存在！", "注册提示");
                }
                catch
                {
                    MessageBox.Show("错误：连接数据库失败！", "注册提示");
                }

            }
        }
    }
}
