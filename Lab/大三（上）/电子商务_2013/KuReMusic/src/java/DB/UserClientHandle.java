/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.*;
/**
 *
 * @author Administrator
 */
public class UserClientHandle {
    Connection con = null;
    Statement stat = null;
    ResultSet rs = null;
    
    public UserClientHandle(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kuremusicdb", "root", "root");
            stat = con.createStatement();
        }catch(Exception e){
            con=null;
        }
    }
    
    public ResultSet executeQuery(String sql){
        try{
            rs = stat.executeQuery(sql);
        }catch(Exception e){
            rs = null;
        }
        return rs;
    }
    
    public int executeUpdate(String sql){
        try{
            stat.executeUpdate(sql);
            return 0;
        }catch(Exception e){
            //更新失败
        }
        return -1;
    }
}
