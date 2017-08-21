package com.example.mylibrary.listener;

import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class ForwardOnDoubleClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        MediaControllerView mediaControllerView = (MediaControllerView)view;
        mediaControllerView.setTime(mediaControllerView.getTime() + 5000);
        mediaControllerView.controllerShow(3000);
    }
}
