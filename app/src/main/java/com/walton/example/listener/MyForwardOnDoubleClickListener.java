package com.walton.example.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class MyForwardOnDoubleClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        MediaControllerView mediaControllerView = (MediaControllerView)view;
        mediaControllerView.setTime(mediaControllerView.getTime() + 5000);
        mediaControllerView.controllerShow(3000);
    }
}