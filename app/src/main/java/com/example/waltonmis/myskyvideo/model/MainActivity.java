package com.example.waltonmis.myskyvideo.model;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mylibrary.view.MediaControllerView;
import com.example.mylibrary.view.MySkyVideoLayoutParams;
import com.example.mylibrary.view.MySkyVideoView;
import com.example.mylibrary.view.VideoPlayerView;
import com.example.waltonmis.myskyvideo.Listener.MyForwardOnDoubleClickListener;
import com.example.waltonmis.myskyvideo.Listener.MyRewindOnDoubleClickListener;
import com.example.waltonmis.myskyvideo.R;

public class MainActivity extends AppCompatActivity {
    MySkyVideoView mySkyVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySkyVideoView = new MySkyVideoView(this);
        mySkyVideoView.setUri(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fhd0085));
        addContentView(mySkyVideoView,new MySkyVideoLayoutParams());
        mySkyVideoView.setLeftOnDoubleListener(new MyRewindOnDoubleClickListener());
        mySkyVideoView.setRightOnDoubleListener(new MyForwardOnDoubleClickListener());
        mySkyVideoView.setMiddleOnClickListener(new MyForwardOnDoubleClickListener());
    }
}