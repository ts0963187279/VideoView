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

import com.walton.example.listener.*;
import com.walton.example.R;
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
	//	videoPlayerView.setLeftOnDoubleClickListener(new LeftOnDoubleClickListener());
	//	videoPlayerView.setLeftOnMoveVerticallyListener(new LeftOnMoveVerticallyListener());
	//	videoPlayerView.setMiddleOnClickListener(new MiddleOnClickListener());
	//	videoPlayerView.setRightOnDoubleClickListener(new RightOnDoubleClickListener());
	//	videoPlayerView.setRightOnMoveVerticallyListener(new RightOnMoveVerticallyListener());
	//	videoPlayerView.setOnMoveHorizontallyListener(new AllOnMoveHorizontallyListener());
	//	VideoFile videoFile = new VideoFile();
	//	videoFile.addObserver(videoPlayerView);
	//	videoFile.loadUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
	videoPlayerView.setUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
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
