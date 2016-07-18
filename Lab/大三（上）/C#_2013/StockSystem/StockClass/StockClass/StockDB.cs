using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using MySQLDriverCS;

namespace StockClass
{
    //数据库操作
    public class StockDB
    {
        private MySQLConnection tConn = null;
        private MySQLCommand cmd = null;
        private MySQLDataReader tReader = null;
        private MySQLDataAdapter adapter = null;
        //初始化，连接数据库
        public StockDB() {
            try 
            {
                MySQLConnectionString tConnStr = new MySQLConnectionString("localhost", "csharpstocksystemdb", "root", "root", 3306);
                tConn = new MySQLConnection(tConnStr.AsString);
                tConn.Open();
                MySQLCommand cmd4 = new MySQLCommand("set names gb2312", tConn);
                cmd4.ExecuteNonQuery();
            }
            catch { }
            
        }
        //析构函数，关闭数据库
        ~StockDB() {
            try
            {
                tConn.Close();
                tReader.Close();
            }
            catch { }
            
        }
        //执行语句操作
        public MySQLDataReader ExecuteCommand(string command) 
        {
            cmd = new MySQLCommand(command, tConn);
            tReader = cmd.ExecuteReaderEx();
            return this.tReader;
        }

        public MySQLDataAdapter ECByAdapter(string command) 
        {
            adapter = new MySQLDataAdapter(command,tConn);
            return adapter;
        }

    }
}
