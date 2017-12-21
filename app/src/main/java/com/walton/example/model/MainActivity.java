package com.walton.example.model;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.walton.example.listener.MyChangeProgressOnMoveHorizontalListener;
import com.walton.example.listener.MyChangeVolumeOnMoveVerticallyListener;
import com.walton.example.listener.MyPlayOnClickListener;
import com.walton.videostreamview.view.VideoStreamView;
import com.walton.example.listener.MyForwardOnDoubleClickListener;
import com.walton.example.listener.MyRewindOnDoubleClickListener;
import com.walton.waltonmis.myskyvideo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by waltonmis on 2017/8/15.
 */

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
            VideoStreamView videoStreamView;
            videoStreamView = new VideoStreamView(context,inputStream);
            addContentView(videoStreamView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            videoStreamView.setLeftOnDoubleListener(new MyRewindOnDoubleClickListener());
            videoStreamView.setRightOnDoubleListener(new MyForwardOnDoubleClickListener());
            videoStreamView.setMiddleOnClickListener(new MyPlayOnClickListener());
            videoStreamView.setRightOnMoveVerticallyListener(new MyChangeVolumeOnMoveVerticallyListener());
            videoStreamView.setMediaControllerOnMoveHorizontallyListener(new MyChangeProgressOnMoveHorizontalListener());
        }
    }
}