package moneyChange;
import java.net.*;
import java.io.*;
public class urlconnection {
	public String exchangeRate(String waibi,String qian){
        try 
        {
            URL u = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="+qian+waibi+"=X&f=sl1d1t1ba&e=.csv");
            InputStream in = u.openStream( );
            in = new BufferedInputStream(in);
            Reader r = new InputStreamReader(in);
            int c;
            String result="";
            while ((c = r.read( )) != -1) 
            {
                result+=(String.valueOf((char) c));
            }
            Object o = u.getContent( );
            return result.split(",")[1];
        }
        catch (Exception e) 
        {
            e.printStackTrace(); 
        }
        return null;
	}
	
	public static void main(String[] args)
    {
        StringBuffer document = new StringBuffer();
        try 
        {
            URL u = new URL("http://www.baidu£¬com");
            InputStream in = u.openStream( );

            in = new BufferedInputStream(in);

            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read( )) != -1) 
            {
                System.out.print(String.valueOf((char) c));
            }
            Object o = u.getContent( );
        }
        catch (Exception e) 
        {
            e.printStackTrace(); 
        }
        System.out.println(document.toString());
    }
    
}
