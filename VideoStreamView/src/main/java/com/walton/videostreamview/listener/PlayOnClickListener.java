package com.walton.videostreamview.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class PlayOnClickListener implements View.OnClickListener {
    static boolean isPause = false;
    @Override
    public void onClick(View view) {
        MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView)view;
        if(!isPause) {
            mediaControllerButtonView.pause();
            mediaControllerButtonView.controllerShow(1000000);
            isPause = true;
        }else {
            mediaControllerButtonView.start();
            mediaControllerButtonView.controllerShow(3000);
            isPause = false;
        }
    }
}
