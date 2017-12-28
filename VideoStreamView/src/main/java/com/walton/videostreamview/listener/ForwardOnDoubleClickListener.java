package com.walton.videostreamview.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class ForwardOnDoubleClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView)view;
        mediaControllerButtonView.setTime(mediaControllerButtonView.getTime() + 5000);
        mediaControllerButtonView.controllerShow(3000);
    }
}
