package com.walton.videostreamview.processor;

import poisondog.core.Mission;
import android.os.Handler;
import java.io.InputStream;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import android.os.AsyncTask;
import com.walton.videostreamview.view.VideoPlayerView;

public class LoadUrl implements Mission<String>{
    private VideoPlayerView videoPlayerView;
    public LoadUrl(VideoPlayerView videoPlayerView){
	this.videoPlayerView = videoPlayerView;
    }
    public Void execute(final String url) {
	LoadUrlTask loadUrlTask = new LoadUrlTask();
	loadUrlTask.execute(url);
	return null;
    }
    private class LoadUrlTask extends AsyncTask<String, Integer, File>{
	@Override
	protected File doInBackground(String... urls){
	    InputStream inputStream = null;
	    try{
		inputStream = new URL(urls[0]).openStream();
	    } catch (IOException e){
		e.printStackTrace();
	    }
	    File file = null;
	    try {
		file = File.createTempFile("temp",".tmp");
		FileOutputStream out = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
		byte[] buffer = new byte[2048];
		int count;
		while((count = inputStream.read(buffer)) != -1){
		    bufferedOutputStream.write(buffer,0,count);
		    bufferedOutputStream.flush();
		}
		out.flush();
		out.close();
		inputStream.close();
	    }catch (IOException e){
		e.printStackTrace();
	    }
	    return file;
	}
	@Override
	protected void onPostExecute(File file){
	    videoPlayerView.update(file);
	}
    }
}
