namespace StockSystem
{
    partial class Login
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Login));
            this.account = new System.Windows.Forms.Label();
            this.password = new System.Windows.Forms.Label();
            this.input_account = new System.Windows.Forms.TextBox();
            this.input_password = new System.Windows.Forms.TextBox();
            this.button_login = new System.Windows.Forms.Button();
            this.button_regist = new System.Windows.Forms.Button();
            this.accounttype_client = new System.Windows.Forms.RadioButton();
            this.accounttype_admin = new System.Windows.Forms.RadioButton();
            this.SuspendLayout();
            // 
            // account
            // 
            this.account.AutoSize = true;
            this.account.BackColor = System.Drawing.Color.Transparent;
            this.account.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.account.Location = new System.Drawing.Point(281, 248);
            this.account.Margin = new System.Windows.Forms.Padding(5, 0, 5, 0);
            this.account.Name = "account";
            this.account.Size = new System.Drawing.Size(58, 21);
            this.account.TabIndex = 0;
            this.account.Text = "账号：";
            // 
            // password
            // 
            this.password.AutoSize = true;
            this.password.BackColor = System.Drawing.Color.Transparent;
            this.password.Location = new System.Drawing.Point(281, 299);
            this.password.Margin = new System.Windows.Forms.Padding(5, 0, 5, 0);
            this.password.Name = "password";
            this.password.Size = new System.Drawing.Size(58, 21);
            this.password.TabIndex = 1;
            this.password.Text = "密码：";
            // 
            // input_account
            // 
            this.input_account.Location = new System.Drawing.Point(359, 245);
            this.input_account.Margin = new System.Windows.Forms.Padding(5);
            this.input_account.Name = "input_account";
            this.input_account.Size = new System.Drawing.Size(164, 29);
            this.input_account.TabIndex = 4;
            this.input_account.TextChanged += new System.EventHandler(this.input_account_TextChanged);
            // 
            // input_password
            // 
            this.input_password.Location = new System.Drawing.Point(359, 296);
            this.input_password.Margin = new System.Windows.Forms.Padding(5);
            this.input_password.Name = "input_password";
            this.input_password.PasswordChar = '*';
            this.input_password.Size = new System.Drawing.Size(164, 29);
            this.input_password.TabIndex = 5;
            this.input_password.TextChanged += new System.EventHandler(this.input_password_TextChanged);
            // 
            // button_login
            // 
            this.button_login.BackColor = System.Drawing.Color.DodgerBlue;
            this.button_login.Font = new System.Drawing.Font("微软雅黑", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button_login.ForeColor = System.Drawing.Color.White;
            this.button_login.Location = new System.Drawing.Point(240, 386);
            this.button_login.Margin = new System.Windows.Forms.Padding(5);
            this.button_login.Name = "button_login";
            this.button_login.Size = new System.Drawing.Size(125, 39);
            this.button_login.TabIndex = 6;
            this.button_login.Text = "登录";
            this.button_login.UseVisualStyleBackColor = false;
            this.button_login.Click += new System.EventHandler(this.button_login_Click);
            // 
            // button_regist
            // 
            this.button_regist.BackColor = System.Drawing.Color.DodgerBlue;
            this.button_regist.Font = new System.Drawing.Font("微软雅黑", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button_regist.ForeColor = System.Drawing.Color.White;
            this.button_regist.Location = new System.Drawing.Point(432, 386);
            this.button_regist.Margin = new System.Windows.Forms.Padding(5);
            this.button_regist.Name = "button_regist";
            this.button_regist.Size = new System.Drawing.Size(125, 39);
            this.button_regist.TabIndex = 7;
            this.button_regist.Text = "注册";
            this.button_regist.UseVisualStyleBackColor = false;
            this.button_regist.Click += new System.EventHandler(this.button_regist_Click);
            // 
            // accounttype_client
            // 
            this.accounttype_client.AutoSize = true;
            this.accounttype_client.BackColor = System.Drawing.Color.Transparent;
            this.accounttype_client.Location = new System.Drawing.Point(305, 344);
            this.accounttype_client.Name = "accounttype_client";
            this.accounttype_client.Size = new System.Drawing.Size(60, 25);
            this.accounttype_client.TabIndex = 8;
            this.accounttype_client.TabStop = true;
            this.accounttype_client.Text = "客户";
            this.accounttype_client.UseVisualStyleBackColor = false;
            // 
            // accounttype_admin
            // 
            this.accounttype_admin.AutoSize = true;
            this.accounttype_admin.BackColor = System.Drawing.Color.Transparent;
            this.accounttype_admin.Location = new System.Drawing.Point(432, 344);
            this.accounttype_admin.Name = "accounttype_admin";
            this.accounttype_admin.Size = new System.Drawing.Size(76, 25);
            this.accounttype_admin.TabIndex = 9;
            this.accounttype_admin.TabStop = true;
            this.accounttype_admin.Text = "管理员";
            this.accounttype_admin.UseVisualStyleBackColor = false;
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 21F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.ClientSize = new System.Drawing.Size(769, 550);
            this.Controls.Add(this.input_account);
            this.Controls.Add(this.account);
            this.Controls.Add(this.accounttype_admin);
            this.Controls.Add(this.accounttype_client);
            this.Controls.Add(this.button_regist);
            this.Controls.Add(this.button_login);
            this.Controls.Add(this.input_password);
            this.Controls.Add(this.password);
            this.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(785, 588);
            this.MinimumSize = new System.Drawing.Size(785, 588);
            this.Name = "Login";
            this.Text = "股票交易系统——登录界面";
            this.Load += new System.EventHandler(this.Login_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label account;
        private System.Windows.Forms.Label password;
        private System.Windows.Forms.TextBox input_account;
        private System.Windows.Forms.TextBox input_password;
        private System.Windows.Forms.Button button_login;
        private System.Windows.Forms.Button button_regist;
        private System.Windows.Forms.RadioButton accounttype_client;
        private System.Windows.Forms.RadioButton accounttype_admin;
    }
}

