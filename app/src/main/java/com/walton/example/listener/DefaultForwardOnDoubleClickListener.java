package com.walton.example.listener;

import android.view.View;

import com.walton.videostreamview.listener.OnDoubleClickListener;
import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class DefaultForwardOnDoubleClickListener implements OnDoubleClickListener {
    @Override
    public void onDoubleClick(View view) {
        MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView) view;
        mediaControllerButtonView.setTime(mediaControllerButtonView.getTime() + 5000);
        mediaControllerButtonView.controllerShow(3000);
    }
}
