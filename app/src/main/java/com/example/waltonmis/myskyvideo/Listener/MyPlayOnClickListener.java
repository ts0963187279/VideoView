package com.example.waltonmis.myskyvideo.Listener;

import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class MyPlayOnClickListener implements View.OnClickListener {
    static boolean isPause = false;
    @Override
    public void onClick(View view) {
        MediaControllerView mediaControllerView = (MediaControllerView)view;
        if(!isPause) {
            mediaControllerView.pause();
            mediaControllerView.controllerShow(1000000);
            isPause = true;
        }else {
            mediaControllerView.start();
            mediaControllerView.controllerShow(3000);
            isPause = false;
        }
    }
}
