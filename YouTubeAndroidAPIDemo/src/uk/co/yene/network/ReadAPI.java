package uk.co.yene.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

 
import android.util.Log;
 
public class ReadAPI {
 
    private static final String TAG = "READ API";
	static InputStream is = null;
    
    private String data="";
    // constructor
    public ReadAPI() {
    	 Log.e(TAG,"Get data from web has started");
    }
    public void ReadBasicLevel(String dataUrl){
    	
    	try {
			URL youtube = new URL(dataUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(youtube.openStream()));
			setData(in.readLine());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void sendGet(String url) throws Exception {
    	 
    	HttpClient client = new DefaultHttpClient();
    	HttpGet request = new HttpGet("http://www.maybeoneday.com");
    	HttpResponse response = client.execute(request);

    	// Get the response
    	BufferedReader rd = new BufferedReader
    	  (new InputStreamReader(response.getEntity().getContent()));
    	    
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	  Log.e(TAG,line);
    	} 
 
	}
}

