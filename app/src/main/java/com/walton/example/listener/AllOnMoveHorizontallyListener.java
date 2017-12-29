package com.walton.example.listener;

import android.view.View;

import com.walton.videostreamview.listener.OnMoveHorizontallyListener;
import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class AllOnMoveHorizontallyListener extends OnMoveHorizontallyListener {
    @Override
    public void onMoveHorizontally(View view,float xPosition) {
	MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView)view;
        mediaControllerButtonView.controllerShow(1000);
        mediaControllerButtonView.pause();
        mediaControllerButtonView.setTime(mediaControllerButtonView.getTime() + (int)(xPosition - getFirstXPosition()) * 75);
        mediaControllerButtonView.start();
        setFirstXPosition(xPosition);
    }
}
