package uk.co.yene.ui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.util.ByteArrayBuffer;

import uk.co.yene.core.PlayList;

import com.examples.youtubeapidemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends BaseAdapter {
	 
    private static final String TAG = "CUSTOMLISTADAPTER";

	private ArrayList<PlayList> listData;
 
    private LayoutInflater layoutInflater;
 
    public CustomListAdapter(Context context, ArrayList<PlayList> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        
    }
 
    @Override
    public int getCount() {
        return listData.size();
    }
 
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.play_list, null);
            holder = new ViewHolder();
            
            holder.toward 		= (TextView) convertView.findViewById(R.id.destinationName);
            holder.busNumber 	= (TextView) convertView.findViewById(R.id.busNumber);
            holder.countDown 	= (TextView) convertView.findViewById(R.id.date);
            holder.busReg 		= (TextView) convertView.findViewById(R.id.busRegNo);
            holder.thumnail		= (ImageView) convertView.findViewById(R.id.list_image);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.toward.setText(listData.get(position).getTitle());
        holder.busNumber.setText(listData.get(position).getDuration());
        holder.countDown.setText(listData.get(position).getVideoid());
        holder.busReg.setText(listData.get(position).getVcount());
        holder.thumnail.setImageBitmap(DownloadFullFromUrl(listData.get(position).getImage()));
        return convertView;
    }
    
    public Bitmap DownloadFullFromUrl(String imageFullURL) {
        Bitmap bm = null;
        try {
            URL url = new URL(imageFullURL);
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            bm = BitmapFactory.decodeByteArray(baf.toByteArray(), 0,
                    baf.toByteArray().length);
        } catch (IOException e) {
            Log.e("ImageManager", "Error: " + e);
        }
        return bm;
    }

 
    public class ViewHolder {
        TextView toward;
        TextView busNumber;
        TextView countDown;
        TextView busReg;
        ImageView thumnail ;
    }
 
}