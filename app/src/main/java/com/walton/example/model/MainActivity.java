package com.walton.example.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.walton.example.listener.DefaultChangeBrightnessOnMoveVerticallyListener;
import com.walton.example.listener.DefaultChangeProgressOnMoveHorizontalListener;
import com.walton.example.listener.DefaultChangeVolumeOnMoveVerticallyListener;
import com.walton.example.listener.DefaultPlayOnClickListener;
import com.walton.videostreamview.view.VideoStreamView;
import com.walton.example.listener.DefaultForwardOnDoubleClickListener;
import com.walton.example.listener.DefaultRewindOnDoubleClickListener;
import com.walton.example.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWRITE_SETTINSPermission();
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
            videoStreamView.setLeftOnDoubleListener(new DefaultRewindOnDoubleClickListener());
            videoStreamView.setRightOnMoveVerticallyListener(new DefaultChangeBrightnessOnMoveVerticallyListener());
            videoStreamView.setRightOnDoubleListener(new DefaultForwardOnDoubleClickListener());
            videoStreamView.setMiddleOnClickListener(new DefaultPlayOnClickListener());
            videoStreamView.setRightOnMoveVerticallyListener(new DefaultChangeVolumeOnMoveVerticallyListener());
            videoStreamView.setMediaControllerOnMoveHorizontallyListener(new DefaultChangeProgressOnMoveHorizontalListener());
        }
    }
    public void getWRITE_SETTINSPermission(){
        if(!Settings.System.canWrite(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(Settings.System.canWrite(this)){
                Toast.makeText(MainActivity.this, "WRITE_SETTINGS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "WRITE_SETTINGS permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
