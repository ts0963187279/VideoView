package com.example.mylibrary.listener;

import android.os.SystemClock;
import android.view.View;

import com.example.mylibrary.view.MediaControllerView;
import com.example.mylibrary.view.VideoPlayerView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class RewindOnDoubleClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        MediaControllerView mediaControllerView = (MediaControllerView)view;
        mediaControllerView.setTime(mediaControllerView.getTime() - 5000);
        mediaControllerView.controllerShow(3000);
    }
}
