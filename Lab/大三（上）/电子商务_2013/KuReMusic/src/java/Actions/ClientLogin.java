/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import DB.UserClientHandle;
import DB.MusicInformation;
/**
 *
 * @author Administrator
 */
public class ClientLogin {
    private String clientid;
    private String clientpassword;
    private String clientpasswordagain;
    private String clientname;
    private String collectid;
    private String searchname;
    private String removeid;
    private UserClientHandle uch = new UserClientHandle();
    public List<MusicInformation> mymusic = new ArrayList<MusicInformation>();
    public List<MusicInformation> allmusic = new ArrayList<MusicInformation>();
    
    public String getClientid() {
        return clientid;
    }
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getClientpassword() {
        return clientpassword;
    }
    public void setClientpassword(String clientpassword) {
        this.clientpassword = clientpassword;
    }
    public String getClientpasswordagain() {
        return clientpasswordagain;
    }
    public void setClientpasswordagain(String clientpasswordagain) {
        this.clientpasswordagain = clientpasswordagain;
    }
    public String getClientname() {
        return clientname;
    }
    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
    public List<MusicInformation> getMymusic(){
        return this.mymusic;
    }
    public void setMymusic(List<MusicInformation> mymusic) {
        this.mymusic = mymusic;
    }
    public List<MusicInformation> getAllmusic(){
        return this.mymusic;
    }
    public void setAllmusic(List<MusicInformation> allmusic) {
        this.allmusic = allmusic;
    }
    public String getCollectid(){
        return this.collectid;
    }
    public void setCollectid(String collectid){
        this.collectid=collectid;
    }
    public String getRemoveid(){
        return this.removeid;
    }
    public void setRemoveid(String removeid){
        this.removeid=removeid;
    }
    public String getSearchname(){
        return this.searchname;
    }
    public void setSearchname(String searchname){
        this.searchname = searchname;
    }
    
    public String remove(){
        String sql = "delete from ownner_music where CLIENT_ID="+getClientid()+" and MUSIC_ID="+getRemoveid()+"";
        uch.executeUpdate(sql);
        login();
        return "ok";
    }
    
    public String search(){
        login();
        List<MusicInformation> searchmusic = new ArrayList<MusicInformation>();
        for(int i=0;i<allmusic.size();i++)
            if(allmusic.get(i).getMUSICname().contains(searchname))
                searchmusic.add(allmusic.get(i));
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("allmusic", searchmusic);
        return "ok";
    }
    
    public String collect(){
        login();
        int my_collectid=Integer.parseInt(collectid);
        for(int i=0;i<mymusic.size();i++){
            if(my_collectid==mymusic.get(i).getMusicId()){
                return "ok";
            }
        }
        String sql = "insert into ownner_music(CLIENT_ID,MUSIC_ID) values("+getClientid()+","+getCollectid()+")";
        int i = uch.executeUpdate(sql);
        if(i>-1){
            sql = "select * from music_information where MUSIC_ID="+this.collectid+"";
            ResultSet rs = uch.executeQuery(sql);
            try{
                if(rs.next()){
                    MusicInformation mi = new MusicInformation();
                    mi.setMUSIClyricurl(rs.getString("MUSIC_lyric_url"));
                    mi.setMUSICname(rs.getString("MUSIC_name"));
                    mi.setMUSICurl(rs.getString("MUSIC_url"));
                    mi.setMUSICvalue(Integer.parseInt(rs.getString("MUSIC_value")));
                    mi.setMusicId(Integer.parseInt(rs.getString("MUSIC_ID")));
                    mymusic.add(mi);
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("mymusic", mymusic);
                }
            }catch(Exception e){}
        }
        return "ok";
    }
    
    public String login()
    {
        String sql = "select * from user_client where CLIENT_ID="+getClientid()+"";
        ResultSet rs = uch.executeQuery(sql);
        try{
            if(!rs.next()){
                return "fail2";
            }
        }catch(Exception e){
            //连接数据库失败
        }
        sql = "select * from user_client where CLIENT_ID="+getClientid()+" and CLIENT_password='"+getClientpassword()+"'";
        rs = uch.executeQuery(sql);
        try{
            if(rs.next()){
                //用户信息
                this.setClientname(rs.getString("CLIENT_name")); 
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("clientpassword", this.clientpassword);
                request.setAttribute("clientname", this.clientname);
                request.setAttribute("clientmoney", rs.getString("CLIENT_money"));
                //用户音乐
                sql = "select * from music_information where MUSIC_ID in(select MUSIC_ID from ownner_music where CLIENT_ID="+this.clientid+")";
                rs = uch.executeQuery(sql);
                while(rs.next()){
                    MusicInformation mi = new MusicInformation();
                    mi.setMUSIClyricurl(rs.getString("MUSIC_lyric_url"));
                    mi.setMUSICname(rs.getString("MUSIC_name"));
                    mi.setMUSICurl(rs.getString("MUSIC_url"));
                    mi.setMUSICvalue(Integer.parseInt(rs.getString("MUSIC_value")));
                    mi.setMusicId(Integer.parseInt(rs.getString("MUSIC_ID")));
                    mymusic.add(mi);
                }
                request.setAttribute("mymusic", this.mymusic);
                //所有音乐
                sql = "select * from music_information";
                rs = uch.executeQuery(sql);
                while(rs.next()){
                    MusicInformation mi = new MusicInformation();
                    mi.setMUSIClyricurl(rs.getString("MUSIC_lyric_url"));
                    mi.setMUSICname(rs.getString("MUSIC_name"));
                    mi.setMUSICurl(rs.getString("MUSIC_url"));
                    mi.setMUSICvalue(Integer.parseInt(rs.getString("MUSIC_value")));
                    mi.setMusicId(Integer.parseInt(rs.getString("MUSIC_ID")));
                    allmusic.add(mi);
                }
                request.setAttribute("allmusic", this.allmusic);
                return "success";
            }
        }catch(Exception e){
            //连接数据库失败
        }
        return "fail1";
    }
    
    public String regist()  
    {
        if(getClientpassword().compareTo(getClientpasswordagain())!=0)//密码不一致
            return "fail1";
        String sql = "select * from user_client where CLIENT_ID="+getClientid()+"";
        ResultSet rs = uch.executeQuery(sql);
        try{
            if(rs.next()){
                return "fail2";//ID已存在
            }
        }catch(Exception e){
            //连接数据库失败
        }
        sql = "select * from user_client where CLIENT_name="+getClientname()+"";
        rs = uch.executeQuery(sql);
        try{
            if(rs.next()){
                return "fail3";//昵称已存在
            }
        }catch(Exception e){
            //连接数据库失败
        }
        
        sql = "insert into user_client(CLIENT_ID,CLIENT_password,CLIENT_name,CLIENT_money) values("+getClientid()+",'"+
                getClientpassword()+"','"+getClientname()+"',0)";
        int i = uch.executeUpdate(sql);
        if(i>-1){
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("clientpassword", this.clientpassword);
            request.setAttribute("clientname", this.clientname);
            request.setAttribute("clientmoney", 0);
            try{
                //用户音乐
                sql = "select * from music_information where MUSIC_ID in(select MUSIC_ID from ownner_music where CLIENT_ID="+this.clientid+")";
                rs = uch.executeQuery(sql);
                while(rs.next()){
                    MusicInformation mi = new MusicInformation();
                    mi.setMUSIClyricurl(rs.getString("MUSIC_lyric_url"));
                    mi.setMUSICname(rs.getString("MUSIC_name"));
                    mi.setMUSICurl(rs.getString("MUSIC_url"));
                    mi.setMUSICvalue(Integer.parseInt(rs.getString("MUSIC_value")));
                    mi.setMusicId(Integer.parseInt(rs.getString("MUSIC_ID")));
                    mymusic.add(mi);
                }
                request.setAttribute("mymusic", this.mymusic);
                //所有音乐
                sql = "select * from music_information";
                rs = uch.executeQuery(sql);
                while(rs.next()){
                    MusicInformation mi = new MusicInformation();
                    mi.setMUSIClyricurl(rs.getString("MUSIC_lyric_url"));
                    mi.setMUSICname(rs.getString("MUSIC_name"));
                    mi.setMUSICurl(rs.getString("MUSIC_url"));
                    mi.setMUSICvalue(Integer.parseInt(rs.getString("MUSIC_value")));
                    mi.setMusicId(Integer.parseInt(rs.getString("MUSIC_ID")));
                    allmusic.add(mi);
                }
                request.setAttribute("allmusic", this.allmusic);
            }catch(Exception e){}
            return "success";
        }
        else{
            return "fail4";
        }
    }
}
