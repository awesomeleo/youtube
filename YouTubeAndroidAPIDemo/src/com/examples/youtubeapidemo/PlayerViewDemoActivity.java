/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.examples.youtubeapidemo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason;
import com.google.android.youtube.player.YouTubeThumbnailView;


import uk.co.yene.helper.Json;
import uk.co.yene.network.ReadAPI;
import uk.co.yene.ui.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple YouTube Android API demo application which shows how to create a simple application that
 * displays a YouTube Video in a {@link YouTubePlayerView}.
 * <p>
 * Note, to use a {@link YouTubePlayerView}, your activity must extend {@link YouTubeBaseActivity}.
 */
public class PlayerViewDemoActivity extends YouTubeBaseActivity implements YouTubeThumbnailView.OnInitializedListener, OnInitializedListener{

	 private YouTubeThumbnailLoader youTubeThumbnailLoader;
	 private YouTubePlayerView youTubeView;
	 private YouTubePlayer player;
	 private ReadAPI readData;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.playerview_demo);
   
    youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
    youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    /*
    youTubeThumbnailView = (YouTubeThumbnailView)findViewById(R.id.youtubethumbnailview);
    youTubeThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    youTubeThumbnailView.setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View arg0) {
    			Log.e("View", ""+arg0.getId());
    			if(youTubeView != null){
    				//player.cueVideo("RBumgq5yVrA");
    				}
    		}});
    
   */
  
  
   readData = new ReadAPI();
   
   new DownloadFilesTask().execute();
   

  }
  
  
  

  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
    if (!wasRestored) {
      //player.cueVideo("RBumgq5yVrA");
      //player.cueVideo("RBumgq5yVrA");
      //player.cuePlaylist("PLB67CD23722776452");
        player.cuePlaylist("PLvnTzo_ZpLAXg43J88kQUyvqqs22sG5mS");
    	this.player = player;
    }
  }

  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
    return (YouTubePlayerView) findViewById(R.id.youtube_view);
  }

@Override
public void onInitializationFailure(YouTubeThumbnailView arg0, YouTubeInitializationResult arg1) {
	 Toast.makeText(getApplicationContext(), "YouTubeThumbnailView.onInitializationFailure()",Toast.LENGTH_LONG).show();
}

@Override
public void onInitializationSuccess(YouTubeThumbnailView thumbnailView, YouTubeThumbnailLoader thumbnailLoader){
	
	  Toast.makeText(getApplicationContext(), "YouTubeThumbnailView.onInitializationSuccess()", Toast.LENGTH_LONG).show();
	  this.youTubeThumbnailLoader = thumbnailLoader;
	  thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());
	       
	  this.youTubeThumbnailLoader.setPlaylist("PLvnTzo_ZpLAU2d0NO-7AUQQlAsOVurQWF");
	 
	
}

private final class ThumbnailLoadedListener implements YouTubeThumbnailLoader.OnThumbnailLoadedListener {

	@Override
public void onThumbnailError(YouTubeThumbnailView arg0, ErrorReason arg1) {
		Toast.makeText(getApplicationContext(), "ThumbnailLoadedListener.onThumbnailError()", Toast.LENGTH_LONG).show();
}

@Override
public void onThumbnailLoaded(YouTubeThumbnailView arg0, String arg1) {
		Toast.makeText(getApplicationContext(), "ThumbnailLoadedListener.onThumbnailLoaded()", Toast.LENGTH_LONG).show();
		Log.e("incoming",""+arg1);
		
		player.cueVideo(arg1);

		}

	}

@Override
public void onInitializationFailure(Provider arg0,YouTubeInitializationResult arg1) {
	// TODO Auto-generated method stub
	
}

private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
	private static final String TAG = "DownloadFilesTask";		
	private String incomingData;
	//private String url = "http://gdata.youtube.com/feeds/api/users/TheYene/uploads?v=2&alt=jsonc";
	//private String url ="http://gdata.youtube.com/feeds/api/playlists/PLB67CD23722776452?v=2&alt=json";
	private String url ="http://gdata.youtube.com/feeds/api/playlists/PLvnTzo_ZpLAWmahgmvKfOmEDH4Tl_Dn-w?v=2&alt=json";
	
	protected void onProgressUpdate(Integer... progress) {
		 Log.e(TAG, "After Data Been read");
     }
     protected void onPostExecute(Long result) {
    		
    		Log.e("Incoming Data Size", ""+incomingData.length());
    		//Log.e("Incoming Data", ""+incomingData);
    		Json readfile = new Json();
    		
    		readfile.convertString(incomingData);
    		
    		final ListView listview = (ListView) findViewById(R.id.listview);
    		listview.setAdapter(new CustomListAdapter(PlayerViewDemoActivity.this, readfile.getPlaylist()));
    		 
    	 }
	@Override
	protected Long doInBackground(URL... params) {
		  
		   readData.ReadBasicLevel(url);
		   incomingData = readData.getData();
		  // Log.e("doInBackground", ""+readData.getData());
		return null;
	}
	
}

}
