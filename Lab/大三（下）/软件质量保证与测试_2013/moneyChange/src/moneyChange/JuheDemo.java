package moneyChange;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.MalformedURLException;

import java.net.URL;

import java.net.URLConnection;
import net.sf.json.JSONObject;

public class JuheDemo {
	String url = "http://download.finance.yahoo.com/d/quotes.csv?s=USDCNY=X";
	public  String loadJSON () {

    StringBuilder json = new StringBuilder();

    try {

        URL oracle = new URL(url);

        URLConnection yc = oracle.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine = null;

        while ( (inputLine = in.readLine()) != null) {

            json.append(inputLine);

        }

        in.close();

    } catch (MalformedURLException e) {

    } catch (IOException e) {

    }

    return json.toString();

}
}
