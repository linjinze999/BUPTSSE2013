namespace StockSystem
{
    partial class Client
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Client));
            this.Client_account = new System.Windows.Forms.TabControl();
            this.ClientAccount = new System.Windows.Forms.TabPage();
            this.CA_holdstock = new System.Windows.Forms.GroupBox();
            this.CA_button_refresh = new System.Windows.Forms.Button();
            this.CA_GV = new System.Windows.Forms.DataGridView();
            this.CA_label_aaccount = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.CA_text_account = new System.Windows.Forms.TextBox();
            this.CA_button_recharge = new System.Windows.Forms.Button();
            this.CA_button_changepassword = new System.Windows.Forms.Button();
            this.CA_text_password = new System.Windows.Forms.TextBox();
            this.CA_text_balance = new System.Windows.Forms.TextBox();
            this.CA_label_password = new System.Windows.Forms.Label();
            this.CA_label_balance = new System.Windows.Forms.Label();
            this.ClientStock = new System.Windows.Forms.TabPage();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.CS_button_refresh2 = new System.Windows.Forms.Button();
            this.CS_GV = new System.Windows.Forms.DataGridView();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.CS_comboBox1 = new System.Windows.Forms.ComboBox();
            this.CS_button1 = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.ClientOrder = new System.Windows.Forms.TabPage();
            this.groupBox7 = new System.Windows.Forms.GroupBox();
            this.button1 = new System.Windows.Forms.Button();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.CO_button_refresh = new System.Windows.Forms.Button();
            this.CO_GV = new System.Windows.Forms.DataGridView();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.CO_button_submit = new System.Windows.Forms.Button();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.CO_rb_buy = new System.Windows.Forms.RadioButton();
            this.CO_rb_sell = new System.Windows.Forms.RadioButton();
            this.groupBox6 = new System.Windows.Forms.GroupBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.CO_comboBox1 = new System.Windows.Forms.ComboBox();
            this.CO_label_number = new System.Windows.Forms.Label();
            this.CO_label_money = new System.Windows.Forms.Label();
            this.CO_label_stockid = new System.Windows.Forms.Label();
            this.Client_account.SuspendLayout();
            this.ClientAccount.SuspendLayout();
            this.CA_holdstock.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.CA_GV)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.ClientStock.SuspendLayout();
            this.groupBox3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.CS_GV)).BeginInit();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.ClientOrder.SuspendLayout();
            this.groupBox7.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.CO_GV)).BeginInit();
            this.groupBox4.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.groupBox6.SuspendLayout();
            this.SuspendLayout();
            // 
            // Client_account
            // 
            this.Client_account.Controls.Add(this.ClientAccount);
            this.Client_account.Controls.Add(this.ClientStock);
            this.Client_account.Controls.Add(this.ClientOrder);
            this.Client_account.Location = new System.Drawing.Point(3, 2);
            this.Client_account.Name = "Client_account";
            this.Client_account.SelectedIndex = 0;
            this.Client_account.Size = new System.Drawing.Size(782, 665);
            this.Client_account.TabIndex = 0;
            // 
            // ClientAccount
            // 
            this.ClientAccount.BackColor = System.Drawing.Color.Transparent;
            this.ClientAccount.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("ClientAccount.BackgroundImage")));
            this.ClientAccount.Controls.Add(this.CA_holdstock);
            this.ClientAccount.Controls.Add(this.groupBox1);
            this.ClientAccount.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ClientAccount.Location = new System.Drawing.Point(4, 22);
            this.ClientAccount.Name = "ClientAccount";
            this.ClientAccount.Padding = new System.Windows.Forms.Padding(3);
            this.ClientAccount.Size = new System.Drawing.Size(774, 639);
            this.ClientAccount.TabIndex = 0;
            this.ClientAccount.Text = "账户查询";
            // 
            // CA_holdstock
            // 
            this.CA_holdstock.Controls.Add(this.CA_button_refresh);
            this.CA_holdstock.Controls.Add(this.CA_GV);
            this.CA_holdstock.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CA_holdstock.Location = new System.Drawing.Point(6, 300);
            this.CA_holdstock.Name = "CA_holdstock";
            this.CA_holdstock.Size = new System.Drawing.Size(765, 333);
            this.CA_holdstock.TabIndex = 4;
            this.CA_holdstock.TabStop = false;
            this.CA_holdstock.Text = " 持有股票 ";
            // 
            // CA_button_refresh
            // 
            this.CA_button_refresh.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CA_button_refresh.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CA_button_refresh.Location = new System.Drawing.Point(672, 294);
            this.CA_button_refresh.Name = "CA_button_refresh";
            this.CA_button_refresh.Size = new System.Drawing.Size(84, 32);
            this.CA_button_refresh.TabIndex = 1;
            this.CA_button_refresh.Text = "刷新";
            this.CA_button_refresh.UseVisualStyleBackColor = false;
            this.CA_button_refresh.Click += new System.EventHandler(this.CA_button_refresh_Click);
            // 
            // CA_GV
            // 
            this.CA_GV.AllowUserToAddRows = false;
            this.CA_GV.AllowUserToDeleteRows = false;
            this.CA_GV.BackgroundColor = System.Drawing.SystemColors.ButtonHighlight;
            this.CA_GV.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.CA_GV.GridColor = System.Drawing.SystemColors.Window;
            this.CA_GV.Location = new System.Drawing.Point(6, 23);
            this.CA_GV.Name = "CA_GV";
            this.CA_GV.ReadOnly = true;
            this.CA_GV.RowTemplate.Height = 23;
            this.CA_GV.Size = new System.Drawing.Size(753, 268);
            this.CA_GV.TabIndex = 0;
            this.CA_GV.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // CA_label_aaccount
            // 
            this.CA_label_aaccount.AutoSize = true;
            this.CA_label_aaccount.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CA_label_aaccount.ForeColor = System.Drawing.Color.Black;
            this.CA_label_aaccount.Location = new System.Drawing.Point(46, 60);
            this.CA_label_aaccount.Name = "CA_label_aaccount";
            this.CA_label_aaccount.Size = new System.Drawing.Size(67, 21);
            this.CA_label_aaccount.TabIndex = 0;
            this.CA_label_aaccount.Text = "账户：";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.CA_text_account);
            this.groupBox1.Controls.Add(this.CA_label_aaccount);
            this.groupBox1.Controls.Add(this.CA_button_recharge);
            this.groupBox1.Controls.Add(this.CA_button_changepassword);
            this.groupBox1.Controls.Add(this.CA_text_password);
            this.groupBox1.Controls.Add(this.CA_text_balance);
            this.groupBox1.Controls.Add(this.CA_label_password);
            this.groupBox1.Controls.Add(this.CA_label_balance);
            this.groupBox1.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.groupBox1.ForeColor = System.Drawing.Color.Black;
            this.groupBox1.Location = new System.Drawing.Point(6, 6);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(762, 278);
            this.groupBox1.TabIndex = 8;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = " 个人信息 ";
            // 
            // CA_text_account
            // 
            this.CA_text_account.BackColor = System.Drawing.Color.AliceBlue;
            this.CA_text_account.Location = new System.Drawing.Point(131, 57);
            this.CA_text_account.Name = "CA_text_account";
            this.CA_text_account.ReadOnly = true;
            this.CA_text_account.Size = new System.Drawing.Size(129, 32);
            this.CA_text_account.TabIndex = 2;
            // 
            // CA_button_recharge
            // 
            this.CA_button_recharge.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CA_button_recharge.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CA_button_recharge.ForeColor = System.Drawing.Color.Black;
            this.CA_button_recharge.Location = new System.Drawing.Point(293, 129);
            this.CA_button_recharge.Name = "CA_button_recharge";
            this.CA_button_recharge.Size = new System.Drawing.Size(115, 32);
            this.CA_button_recharge.TabIndex = 8;
            this.CA_button_recharge.Text = "充值";
            this.CA_button_recharge.UseVisualStyleBackColor = false;
            this.CA_button_recharge.Click += new System.EventHandler(this.button1_Click);
            // 
            // CA_button_changepassword
            // 
            this.CA_button_changepassword.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CA_button_changepassword.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CA_button_changepassword.ForeColor = System.Drawing.Color.Black;
            this.CA_button_changepassword.Location = new System.Drawing.Point(293, 202);
            this.CA_button_changepassword.Name = "CA_button_changepassword";
            this.CA_button_changepassword.Size = new System.Drawing.Size(115, 34);
            this.CA_button_changepassword.TabIndex = 7;
            this.CA_button_changepassword.Text = "修改密码";
            this.CA_button_changepassword.UseVisualStyleBackColor = false;
            this.CA_button_changepassword.Click += new System.EventHandler(this.CA_button_changepassword_Click);
            // 
            // CA_text_password
            // 
            this.CA_text_password.BackColor = System.Drawing.Color.AliceBlue;
            this.CA_text_password.Location = new System.Drawing.Point(131, 202);
            this.CA_text_password.Name = "CA_text_password";
            this.CA_text_password.PasswordChar = '*';
            this.CA_text_password.ReadOnly = true;
            this.CA_text_password.Size = new System.Drawing.Size(130, 32);
            this.CA_text_password.TabIndex = 6;
            // 
            // CA_text_balance
            // 
            this.CA_text_balance.BackColor = System.Drawing.Color.AliceBlue;
            this.CA_text_balance.Location = new System.Drawing.Point(131, 129);
            this.CA_text_balance.Name = "CA_text_balance";
            this.CA_text_balance.ReadOnly = true;
            this.CA_text_balance.Size = new System.Drawing.Size(129, 32);
            this.CA_text_balance.TabIndex = 3;
            // 
            // CA_label_password
            // 
            this.CA_label_password.AutoSize = true;
            this.CA_label_password.ForeColor = System.Drawing.Color.Black;
            this.CA_label_password.Location = new System.Drawing.Point(46, 205);
            this.CA_label_password.Name = "CA_label_password";
            this.CA_label_password.Size = new System.Drawing.Size(67, 21);
            this.CA_label_password.TabIndex = 5;
            this.CA_label_password.Text = "密码：";
            // 
            // CA_label_balance
            // 
            this.CA_label_balance.AutoSize = true;
            this.CA_label_balance.ForeColor = System.Drawing.Color.Black;
            this.CA_label_balance.Location = new System.Drawing.Point(46, 132);
            this.CA_label_balance.Name = "CA_label_balance";
            this.CA_label_balance.Size = new System.Drawing.Size(67, 21);
            this.CA_label_balance.TabIndex = 1;
            this.CA_label_balance.Text = "余额：";
            // 
            // ClientStock
            // 
            this.ClientStock.BackColor = System.Drawing.Color.Transparent;
            this.ClientStock.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("ClientStock.BackgroundImage")));
            this.ClientStock.Controls.Add(this.groupBox3);
            this.ClientStock.Controls.Add(this.groupBox2);
            this.ClientStock.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ClientStock.Location = new System.Drawing.Point(4, 22);
            this.ClientStock.Name = "ClientStock";
            this.ClientStock.Padding = new System.Windows.Forms.Padding(3);
            this.ClientStock.Size = new System.Drawing.Size(774, 639);
            this.ClientStock.TabIndex = 1;
            this.ClientStock.Text = "股市查询";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.CS_button_refresh2);
            this.groupBox3.Controls.Add(this.CS_GV);
            this.groupBox3.Location = new System.Drawing.Point(5, 334);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(763, 299);
            this.groupBox3.TabIndex = 1;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "交易详情";
            // 
            // CS_button_refresh2
            // 
            this.CS_button_refresh2.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CS_button_refresh2.Location = new System.Drawing.Point(657, 262);
            this.CS_button_refresh2.Name = "CS_button_refresh2";
            this.CS_button_refresh2.Size = new System.Drawing.Size(78, 30);
            this.CS_button_refresh2.TabIndex = 1;
            this.CS_button_refresh2.Text = "刷新";
            this.CS_button_refresh2.UseVisualStyleBackColor = false;
            this.CS_button_refresh2.Click += new System.EventHandler(this.CS_button_refresh2_Click);
            // 
            // CS_GV
            // 
            this.CS_GV.AllowUserToAddRows = false;
            this.CS_GV.AllowUserToDeleteRows = false;
            this.CS_GV.BackgroundColor = System.Drawing.SystemColors.Window;
            this.CS_GV.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.CS_GV.Location = new System.Drawing.Point(6, 31);
            this.CS_GV.Name = "CS_GV";
            this.CS_GV.ReadOnly = true;
            this.CS_GV.RowTemplate.Height = 23;
            this.CS_GV.Size = new System.Drawing.Size(751, 225);
            this.CS_GV.TabIndex = 0;
            // 
            // groupBox2
            // 
            this.groupBox2.BackColor = System.Drawing.Color.AliceBlue;
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Controls.Add(this.CS_comboBox1);
            this.groupBox2.Controls.Add(this.CS_button1);
            this.groupBox2.Controls.Add(this.pictureBox1);
            this.groupBox2.Location = new System.Drawing.Point(5, 6);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(763, 322);
            this.groupBox2.TabIndex = 0;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "股票走势";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(435, 290);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(105, 21);
            this.label1.TabIndex = 3;
            this.label1.Text = "选择股票：";
            // 
            // CS_comboBox1
            // 
            this.CS_comboBox1.BackColor = System.Drawing.Color.AliceBlue;
            this.CS_comboBox1.FormattingEnabled = true;
            this.CS_comboBox1.Location = new System.Drawing.Point(546, 287);
            this.CS_comboBox1.Name = "CS_comboBox1";
            this.CS_comboBox1.Size = new System.Drawing.Size(121, 29);
            this.CS_comboBox1.TabIndex = 2;
            // 
            // CS_button1
            // 
            this.CS_button1.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CS_button1.Location = new System.Drawing.Point(673, 287);
            this.CS_button1.Name = "CS_button1";
            this.CS_button1.Size = new System.Drawing.Size(75, 29);
            this.CS_button1.TabIndex = 1;
            this.CS_button1.Text = "确认";
            this.CS_button1.UseVisualStyleBackColor = false;
            this.CS_button1.Click += new System.EventHandler(this.CS_button1_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Location = new System.Drawing.Point(6, 21);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(751, 260);
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // ClientOrder
            // 
            this.ClientOrder.BackColor = System.Drawing.Color.Transparent;
            this.ClientOrder.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("ClientOrder.BackgroundImage")));
            this.ClientOrder.Controls.Add(this.groupBox7);
            this.ClientOrder.Controls.Add(this.groupBox4);
            this.ClientOrder.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ClientOrder.Location = new System.Drawing.Point(4, 22);
            this.ClientOrder.Name = "ClientOrder";
            this.ClientOrder.Padding = new System.Windows.Forms.Padding(3);
            this.ClientOrder.Size = new System.Drawing.Size(774, 639);
            this.ClientOrder.TabIndex = 2;
            this.ClientOrder.Text = "订单操作";
            // 
            // groupBox7
            // 
            this.groupBox7.Controls.Add(this.button1);
            this.groupBox7.Controls.Add(this.comboBox1);
            this.groupBox7.Controls.Add(this.label2);
            this.groupBox7.Controls.Add(this.CO_button_refresh);
            this.groupBox7.Controls.Add(this.CO_GV);
            this.groupBox7.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.groupBox7.Location = new System.Drawing.Point(6, 285);
            this.groupBox7.Name = "groupBox7";
            this.groupBox7.Size = new System.Drawing.Size(762, 341);
            this.groupBox7.TabIndex = 3;
            this.groupBox7.TabStop = false;
            this.groupBox7.Text = "我的订单";
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.LightSkyBlue;
            this.button1.Location = new System.Drawing.Point(151, 304);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(82, 31);
            this.button1.TabIndex = 4;
            this.button1.Text = "删除";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Location = new System.Drawing.Point(63, 304);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(79, 29);
            this.comboBox1.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(6, 309);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(67, 21);
            this.label2.TabIndex = 2;
            this.label2.Text = "序号：";
            // 
            // CO_button_refresh
            // 
            this.CO_button_refresh.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CO_button_refresh.Location = new System.Drawing.Point(670, 304);
            this.CO_button_refresh.Name = "CO_button_refresh";
            this.CO_button_refresh.Size = new System.Drawing.Size(75, 31);
            this.CO_button_refresh.TabIndex = 1;
            this.CO_button_refresh.Text = "刷新";
            this.CO_button_refresh.UseVisualStyleBackColor = false;
            this.CO_button_refresh.Click += new System.EventHandler(this.CO_button_refresh_Click);
            // 
            // CO_GV
            // 
            this.CO_GV.AllowUserToAddRows = false;
            this.CO_GV.AllowUserToDeleteRows = false;
            this.CO_GV.BackgroundColor = System.Drawing.SystemColors.Window;
            this.CO_GV.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.CO_GV.Location = new System.Drawing.Point(8, 22);
            this.CO_GV.Name = "CO_GV";
            this.CO_GV.ReadOnly = true;
            this.CO_GV.RowTemplate.Height = 23;
            this.CO_GV.Size = new System.Drawing.Size(748, 276);
            this.CO_GV.TabIndex = 0;
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.CO_button_submit);
            this.groupBox4.Controls.Add(this.groupBox5);
            this.groupBox4.Controls.Add(this.groupBox6);
            this.groupBox4.Font = new System.Drawing.Font("华文楷体", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.groupBox4.Location = new System.Drawing.Point(6, 6);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(759, 273);
            this.groupBox4.TabIndex = 2;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "新订单";
            // 
            // CO_button_submit
            // 
            this.CO_button_submit.BackColor = System.Drawing.Color.LightSkyBlue;
            this.CO_button_submit.Location = new System.Drawing.Point(594, 197);
            this.CO_button_submit.Name = "CO_button_submit";
            this.CO_button_submit.Size = new System.Drawing.Size(77, 33);
            this.CO_button_submit.TabIndex = 5;
            this.CO_button_submit.Text = "确认";
            this.CO_button_submit.UseVisualStyleBackColor = false;
            this.CO_button_submit.Click += new System.EventHandler(this.CO_button_submit_Click);
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.CO_rb_buy);
            this.groupBox5.Controls.Add(this.CO_rb_sell);
            this.groupBox5.Location = new System.Drawing.Point(42, 58);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(129, 172);
            this.groupBox5.TabIndex = 9;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "交易类型";
            // 
            // CO_rb_buy
            // 
            this.CO_rb_buy.AutoSize = true;
            this.CO_rb_buy.Location = new System.Drawing.Point(40, 54);
            this.CO_rb_buy.Name = "CO_rb_buy";
            this.CO_rb_buy.Size = new System.Drawing.Size(47, 25);
            this.CO_rb_buy.TabIndex = 3;
            this.CO_rb_buy.TabStop = true;
            this.CO_rb_buy.Text = "买";
            this.CO_rb_buy.UseVisualStyleBackColor = true;
            // 
            // CO_rb_sell
            // 
            this.CO_rb_sell.AutoSize = true;
            this.CO_rb_sell.Location = new System.Drawing.Point(40, 105);
            this.CO_rb_sell.Name = "CO_rb_sell";
            this.CO_rb_sell.Size = new System.Drawing.Size(47, 25);
            this.CO_rb_sell.TabIndex = 4;
            this.CO_rb_sell.TabStop = true;
            this.CO_rb_sell.Text = "卖";
            this.CO_rb_sell.UseVisualStyleBackColor = true;
            // 
            // groupBox6
            // 
            this.groupBox6.Controls.Add(this.textBox1);
            this.groupBox6.Controls.Add(this.textBox2);
            this.groupBox6.Controls.Add(this.CO_comboBox1);
            this.groupBox6.Controls.Add(this.CO_label_number);
            this.groupBox6.Controls.Add(this.CO_label_money);
            this.groupBox6.Controls.Add(this.CO_label_stockid);
            this.groupBox6.Location = new System.Drawing.Point(201, 58);
            this.groupBox6.Name = "groupBox6";
            this.groupBox6.Size = new System.Drawing.Size(344, 172);
            this.groupBox6.TabIndex = 10;
            this.groupBox6.TabStop = false;
            this.groupBox6.Text = "交易内容";
            // 
            // textBox1
            // 
            this.textBox1.BackColor = System.Drawing.Color.AliceBlue;
            this.textBox1.Location = new System.Drawing.Point(138, 76);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(117, 32);
            this.textBox1.TabIndex = 7;
            // 
            // textBox2
            // 
            this.textBox2.BackColor = System.Drawing.Color.AliceBlue;
            this.textBox2.Location = new System.Drawing.Point(138, 121);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(117, 32);
            this.textBox2.TabIndex = 8;
            // 
            // CO_comboBox1
            // 
            this.CO_comboBox1.BackColor = System.Drawing.Color.AliceBlue;
            this.CO_comboBox1.FormattingEnabled = true;
            this.CO_comboBox1.Location = new System.Drawing.Point(138, 36);
            this.CO_comboBox1.Name = "CO_comboBox1";
            this.CO_comboBox1.Size = new System.Drawing.Size(117, 29);
            this.CO_comboBox1.TabIndex = 6;
            // 
            // CO_label_number
            // 
            this.CO_label_number.AutoSize = true;
            this.CO_label_number.Location = new System.Drawing.Point(65, 124);
            this.CO_label_number.Name = "CO_label_number";
            this.CO_label_number.Size = new System.Drawing.Size(67, 21);
            this.CO_label_number.TabIndex = 2;
            this.CO_label_number.Text = "数量：";
            // 
            // CO_label_money
            // 
            this.CO_label_money.AutoSize = true;
            this.CO_label_money.Location = new System.Drawing.Point(65, 80);
            this.CO_label_money.Name = "CO_label_money";
            this.CO_label_money.Size = new System.Drawing.Size(67, 21);
            this.CO_label_money.TabIndex = 1;
            this.CO_label_money.Text = "金额：";
            // 
            // CO_label_stockid
            // 
            this.CO_label_stockid.AutoSize = true;
            this.CO_label_stockid.Location = new System.Drawing.Point(46, 39);
            this.CO_label_stockid.Name = "CO_label_stockid";
            this.CO_label_stockid.Size = new System.Drawing.Size(86, 21);
            this.CO_label_stockid.TabIndex = 0;
            this.CO_label_stockid.Text = "下单股：";
            // 
            // Client
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 662);
            this.Controls.Add(this.Client_account);
            this.MaximumSize = new System.Drawing.Size(800, 700);
            this.MinimumSize = new System.Drawing.Size(800, 700);
            this.Name = "Client";
            this.Text = "股票交易系统——客户端";
            this.Load += new System.EventHandler(this.Client_Load);
            this.Client_account.ResumeLayout(false);
            this.ClientAccount.ResumeLayout(false);
            this.CA_holdstock.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.CA_GV)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ClientStock.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.CS_GV)).EndInit();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ClientOrder.ResumeLayout(false);
            this.groupBox7.ResumeLayout(false);
            this.groupBox7.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.CO_GV)).EndInit();
            this.groupBox4.ResumeLayout(false);
            this.groupBox5.ResumeLayout(false);
            this.groupBox5.PerformLayout();
            this.groupBox6.ResumeLayout(false);
            this.groupBox6.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl Client_account;
        private System.Windows.Forms.TabPage ClientAccount;
        private System.Windows.Forms.TabPage ClientStock;
        private System.Windows.Forms.TabPage ClientOrder;
        private System.Windows.Forms.GroupBox CA_holdstock;
        private System.Windows.Forms.TextBox CA_text_balance;
        private System.Windows.Forms.TextBox CA_text_account;
        private System.Windows.Forms.Label CA_label_balance;
        private System.Windows.Forms.Label CA_label_aaccount;
        private System.Windows.Forms.Label CA_label_password;
        private System.Windows.Forms.TextBox CA_text_password;
        private System.Windows.Forms.Button CA_button_changepassword;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button CA_button_recharge;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox7;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.ComboBox CO_comboBox1;
        private System.Windows.Forms.Button CO_button_submit;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.RadioButton CO_rb_buy;
        private System.Windows.Forms.RadioButton CO_rb_sell;
        private System.Windows.Forms.GroupBox groupBox6;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Label CO_label_number;
        private System.Windows.Forms.Label CO_label_money;
        private System.Windows.Forms.Label CO_label_stockid;
        private System.Windows.Forms.DataGridView CA_GV;
        private System.Windows.Forms.Button CA_button_refresh;
        private System.Windows.Forms.DataGridView CO_GV;
        private System.Windows.Forms.Button CO_button_refresh;
        private System.Windows.Forms.Button CS_button_refresh2;
        private System.Windows.Forms.DataGridView CS_GV;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Button CS_button1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox CS_comboBox1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.Label label2;
    }
}