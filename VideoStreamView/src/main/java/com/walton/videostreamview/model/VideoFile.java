/*
 * Copyright (C) 2018 RS Wong <ts0963187279@gmail.com>
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
package com.walton.videostreamview.model;
import com.walton.videostreamview.processor.Subject;
import com.walton.videostreamview.processor.Observer;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.net.URL;
import android.os.AsyncTask;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class VideoFile implements Subject{
    private File file = null;
    private List<Observer> observers = new ArrayList<Observer>();
    public void setFile(File file){
	this.file = file;
	updateObservers(file);
    }
    public void updateObservers(File file){
	for(Observer observer : observers){
	    observer.update(file);
	}
    }
    public void addObserver(Observer observer){
	observers.add(observer);
    }
    public void deleteObserver(Observer observer){
	observers.remove(observer);
    }
    public void loadUrl(String url){
	new LoadUrl().execute(url);
    }
    private class LoadUrl extends AsyncTask<String, Integer, File>{
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
	    setFile(file);
	}
    }
}
