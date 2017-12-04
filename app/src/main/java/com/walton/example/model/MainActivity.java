package com.walton.waltonmis.myskyvideo.model;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.walton.mylibrary.view.MySkyVideoLayoutParams;
import com.walton.mylibrary.view.MySkyVideoView;
import com.walton.waltonmis.myskyvideo.Listener.MyForwardOnDoubleClickListener;
import com.walton.waltonmis.myskyvideo.Listener.MyRewindOnDoubleClickListener;
import com.walton.waltonmis.myskyvideo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadVideo(this).execute("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
    }
    private class LoadVideo extends AsyncTask<String, Void, InputStream> {
        private Context context;

        public LoadVideo(Context context) {
            this.context = context;
        }
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                inputStream = new URL(strings[0]).openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }
        @Override
        protected void onPostExecute(InputStream inputStream) {
            MySkyVideoView mySkyVideoView;
            mySkyVideoView = new MySkyVideoView(context,inputStream);
            addContentView(mySkyVideoView, new MySkyVideoLayoutParams());
            mySkyVideoView.setLeftOnDoubleListener(new MyRewindOnDoubleClickListener());
            mySkyVideoView.setRightOnDoubleListener(new MyForwardOnDoubleClickListener());
            mySkyVideoView.setMiddleOnClickListener(new MyForwardOnDoubleClickListener());
        }
    }
}