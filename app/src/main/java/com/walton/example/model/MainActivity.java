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
import com.walton.example.listener.DefaultForwardOnDoubleClickListener;
import com.walton.example.listener.DefaultRewindOnDoubleClickListener;
import com.walton.example.R;
import com.walton.videostreamview.model.VideoFile;
import com.walton.videostreamview.view.VideoPlayerView;
import android.widget.LinearLayout.LayoutParams;

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
	VideoPlayerView videoPlayerView = new VideoPlayerView(this);
	videoPlayerView.setLeftOnDoubleClickListener(new DefaultRewindOnDoubleClickListener());
	videoPlayerView.setLeftOnMoveVerticallyListener(new DefaultChangeBrightnessOnMoveVerticallyListener());
	videoPlayerView.setMiddleOnClickListener(new DefaultPlayOnClickListener());
	videoPlayerView.setRightOnDoubleClickListener(new DefaultForwardOnDoubleClickListener());
	videoPlayerView.setRightOnMoveVerticallyListener(new DefaultChangeVolumeOnMoveVerticallyListener());
	videoPlayerView.setOnMoveHorizontallyListener(new DefaultChangeProgressOnMoveHorizontalListener());
	VideoFile videoFile = new VideoFile();
	videoFile.addObserver(videoPlayerView);
	videoFile.loadUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
	addContentView(videoPlayerView,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
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
