package uk.co.yene.helper;

import java.util.ArrayList;

import uk.co.yene.core.PlayList;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Json {
	
	private JsonParser parser;

    private ArrayList<PlayList> playlist = new ArrayList<PlayList>();
	public Json(){
		parser = new JsonParser();
	}
	
	public void convertString(String data){
		
		
		JsonObject feed = (JsonObject)parser.parse(data);
		//System.out.print("\n"+feed.get("feed"));
		
		JsonObject entry = (JsonObject)feed.get("feed");
		//System.out.print("\n"+entry.isJsonObject());
		
		JsonArray eachEntry = (JsonArray)entry.get("entry");
		System.out.print("\n each: "+eachEntry.size());
		
		for(int x =0; x< eachEntry.size(); x++){
			//System.out.print("\n : "+eachEntry..size());
			JsonObject insideEachItem = (JsonObject)eachEntry.get(x);
			//System.out.print("\n : "+insideEachItem.get("media$group"));
			media$group(insideEachItem.get("media$group"));
			yt$statistics(insideEachItem.get("yt$statistics"));
		}
		
		
		
	}
	
	
	public void media$group(JsonElement o){
		
		JsonObject media$group = (JsonObject)o;
		
		JsonObject description 	= (JsonObject)media$group.get("media$description");
		JsonObject duration 	= (JsonObject)media$group.get("yt$duration");
		JsonObject title 		= (JsonObject)media$group.get("media$title");
		JsonArray  image 		= (JsonArray)media$group.get("media$thumbnail");
		JsonObject videoid 		= (JsonObject)media$group.get("yt$videoid");
		
		JsonObject imageURL 		= (JsonObject)image.get(2);
		String d,t,v,dur,i;
		
		d 	= ""+description.get("$t");
		t 	=""+title.get("$t");
		v 	=""+videoid.get("$t");
		dur =""+duration.get("seconds");
		i 	=""+imageURL.get("url").getAsString().replace("\"", "");
//		System.out.print("\n Des : "+description.get("$t"));
//		System.out.print("\n Title: "+title.get("$t"));
//		System.out.print("\n Vid: "+videoid.get("$t"));
//		System.out.print("\n Time: "+duration.get("seconds"));
//		System.out.print("\n Image: "+imageURL.get("url"));
		Log.e("image", i);
		//playlist.add(d);
		//PlayList(String d , String time , String title, String image , String vid, String fav, String viewcount){
		playlist.add(new PlayList(d,dur,t,i,v,"",""));
		
	}
	
	public void yt$statistics(JsonElement o){
		
		JsonObject statistics = (JsonObject)o;
		
		System.out.print("\n view: "+statistics.get("viewCount"));
		System.out.print("\n fav: "+statistics.get("favoriteCount"));
		System.out.print("\n===========================");
	}

	public ArrayList<PlayList> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(ArrayList<PlayList> playlist) {
		this.playlist = playlist;
	}

}
